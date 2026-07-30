const API_PEDIDOS = "http://localhost:8080/pedidos";
const API_DETALLES = "http://localhost:8080/detalle-pedidos";
const URL_UPLOADS = "http://localhost:8080/uploads/comprobantes/";

// Estados válidos del ciclo de vida de un pedido
const ESTADOS_PEDIDO = {
    pendiente_efectivo: "Pendiente de pago (efectivo)",
    pendiente_revision: "Pago en revisión (Nequi)",
    pagado: "Pagado",
    en_preparacion: "En preparación",
    en_ruta: "En ruta",
    entregado: "Entregado",
    cancelado: "Cancelado"
};

// EVENTO
btnVerPedidos.addEventListener("click", cargarPedidos);

// CARGAR PEDIDOS
async function cargarPedidos() {
    try {
        listaProductos.style.display = "none";
        formularioProducto.style.display = "none";
        listaPedidos.style.display = "block";

        const respuesta = await fetch(API_PEDIDOS);
        const pedidos = await respuesta.json();

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
                // Si la cadena guardada ya incluye http:// o https:// se usa tal cual,
                // de lo contrario se concatena el servidor de uploads de Spring Boot
                const rutaImagen = pedido.comprobante.startsWith("http")
                    ? pedido.comprobante
                    : `${URL_UPLOADS}${pedido.comprobante}`;

                comprobanteHtml = `
                    <a href="${rutaImagen}" target="_blank">
                        <img src="${rutaImagen}" alt="Comprobante" style="max-width:60px; border-radius:6px; cursor:pointer;">
                    </a>
                    ${pedido.numeroNequi ? `<br><small>Nº: ${pedido.numeroNequi}</small>` : ""}
                `;
            }

            const opcionesEstado = Object.keys(ESTADOS_PEDIDO).map((valor) =>
                `<option value="${valor}" ${pedido.estado === valor ? "selected" : ""}>${ESTADOS_PEDIDO[valor]}</option>`
            ).join("");

            const ESTADOS_PENDIENTES_DE_PAGO = ["pendiente_efectivo", "pendiente_revision"];

            const botonConfirmar = ESTADOS_PENDIENTES_DE_PAGO.includes(pedido.estado)
                ? `<button onclick="confirmarPago(${pedido.idPedido})" class="btn-editar">Confirmar pago</button>`
                : "";

            html += `
                <tr>
                    <td>${pedido.idPedido}</td>
                    <td>${pedido.nombreCliente || "—"} <small>(ID: ${pedido.idCliente})</small></td>
                    <td>${pedido.fecha}</td>
                    <td>
                        <span class="estado-${pedido.estado}">●</span>
                        <select onchange="actualizarEstado(${pedido.idPedido}, this.value)" class="select-estado">
                            ${opcionesEstado}
                        </select>
                    </td>
                    <td>$${pedido.total}</td>
                    <td>${metodoPago}</td>
                    <td>${comprobanteHtml}</td>
                    <td>
                        ${botonConfirmar}
                        <button onclick="verProductos(${pedido.idPedido})" class="btn-editar">
                            Ver productos
                        </button>
                    </td>
                </tr>
                <tr id="productos-${pedido.idPedido}" class="fila-productos" style="display:none;">
                    <td colspan="8"></td>
                </tr>
            `;
        });

        html += `
                </tbody>
            </table>
        `;

        listaPedidos.innerHTML = html;

    } catch (error) {
        console.error("Error al cargar pedidos:", error);
    }
}

// CONFIRMAR PAGO (aprobación manual del comprobante)
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

// ACTUALIZAR ESTADO (cambia el estado desde el selector desplegable)
async function actualizarEstado(id, nuevoEstado) {
    try {
        const respuesta = await fetch(`${API_PEDIDOS}/${id}`);
        const pedido = await respuesta.json();

        pedido.estado = nuevoEstado;

        await fetch(`${API_PEDIDOS}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(pedido)
        });

        cargarPedidos();

    } catch (error) {
        console.error("Error actualizando pedido:", error);
    }
}

// VER PRODUCTOS DEL PEDIDO
async function verProductos(idPedido) {
    const fila = document.getElementById(`productos-${idPedido}`);
    if (!fila) return;

    if (fila.style.display === "table-row") {
        fila.style.display = "none";
        return;
    }

    try {
        const respuesta = await fetch(`${API_DETALLES}/pedido/${idPedido}`);
        const detalles = await respuesta.json();

        const contenido = detalles.length === 0
            ? "<p>Este pedido no tiene productos registrados.</p>"
            : `
                <ul class="lista-productos-pedido">
                    ${detalles.map((d) => `
                        <li>${d.cantidad} × ${d.nombreProducto} — $${d.subtotal}</li>
                    `).join("")}
                </ul>
            `;

        fila.querySelector("td").innerHTML = contenido;
        fila.style.display = "table-row";

    } catch (error) {
        console.error("Error al cargar los productos del pedido:", error);
    }
}