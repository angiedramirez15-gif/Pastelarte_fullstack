const API_PEDIDOS =
    "http://localhost:8080/pedidos";

// EVENTO

btnVerPedidos.addEventListener(
    "click",
    cargarPedidos
);

// CARGAR PEDIDOS

async function cargarPedidos() {

    try {

        listaProductos.style.display = "none";

        formularioProducto.style.display = "none";

        listaPedidos.style.display = "block";

        const respuesta =
            await fetch(API_PEDIDOS);

        const pedidos =
            await respuesta.json();

        let html = `
            <table class="tabla-productos">

                <thead>

                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th>Total</th>
                        <th>Método de pago</th>
                        <th>Comprobante</th>
                        <th>Acciones</th>
                    </tr>

                </thead>

                <tbody>
        `;

        pedidos.forEach((pedido) => {

            const metodoPago = pedido.idPago === 1 ? "Nequi 💜" : "Efectivo 💵";

            let comprobanteHtml = "—";
            if (pedido.comprobante) {
                comprobanteHtml = `
                    <a href="${pedido.comprobante}" target="_blank">
                        <img src="${pedido.comprobante}" alt="Comprobante" style="max-width:60px; border-radius:6px;">
                    </a>
                    ${pedido.numeroNequi ? `<br><small>Nº: ${pedido.numeroNequi}</small>` : ""}
                `;
            }

            const botonConfirmar = pedido.estado === "pagado"
                ? ""
                : `<button onclick="confirmarPago(${pedido.idPedido})" class="btn-editar">Confirmar pago</button>`;

            html += `
                <tr>

                    <td>${pedido.idPedido}</td>

                    <td>${pedido.idCliente}</td>

                    <td>${pedido.fecha}</td>

                    <td><span class="estado-${pedido.estado}">${pedido.estado}</span></td>

                    <td>$${pedido.total}</td>

                    <td>${metodoPago}</td>

                    <td>${comprobanteHtml}</td>

                    <td>

                        ${botonConfirmar}

                        <button
                            onclick="cambiarEstado(${pedido.idPedido})"
                            class="btn-editar"
                        >
                            Cambiar estado
                        </button>

                        <button
                            onclick="eliminarPedido(${pedido.idPedido})"
                            class="btn-eliminar"
                        >
                            Eliminar
                        </button>

                    </td>

                </tr>
            `;
        });

        html += `
                </tbody>

            </table>
        `;

        listaPedidos.innerHTML = html;

    } catch (error) {

        console.error(
            "Error al cargar pedidos:",
            error
        );
    }
}

// ELIMINAR PEDIDO

async function eliminarPedido(id) {

    const confirmar =
        confirm("¿Eliminar pedido?");

    if (!confirmar) return;

    try {

        await fetch(`${API_PEDIDOS}/${id}`, {
            method: "DELETE"
        });

        alert("Pedido eliminado");

        cargarPedidos();

    } catch (error) {

        console.error(
            "Error eliminando pedido:",
            error
        );
    }
}

// CONFIRMAR PAGO (revisas el comprobante en tu celular y lo apruebas)

async function confirmarPago(id) {

    const confirmar = confirm("¿Confirmar que el pago de este pedido es válido?");

    if (!confirmar) return;

    try {

        await fetch(`${API_PEDIDOS}/${id}/confirmar-pago`, {
            method: "PUT"
        });

        alert("✅ Pago confirmado. El pedido ahora está marcado como 'pagado'.");

        cargarPedidos();

    } catch (error) {
        console.error("Error confirmando el pago:", error);
    }
}

// CAMBIAR ESTADO

async function cambiarEstado(id) {

    const nuevoEstado = prompt(
        "Nuevo estado: pendiente, pagado o cancelado"
    );

    if (!nuevoEstado) return;

    try {

        const respuesta =
            await fetch(`${API_PEDIDOS}/${id}`);

        const pedido =
            await respuesta.json();

        pedido.estado = nuevoEstado;

        await fetch(`${API_PEDIDOS}/${id}`, {

            method: "PUT",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(pedido)
        });

        alert("Estado actualizado");

        cargarPedidos();

    } catch (error) {

        console.error(
            "Error actualizando pedido:",
            error
        );
    }
}