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
                        <th>Pago</th>
                        <th>Acciones</th>
                    </tr>

                </thead>

                <tbody>
        `;

        pedidos.forEach((pedido) => {

            html += `
                <tr>

                    <td>${pedido.idPedido}</td>

                    <td>${pedido.idCliente}</td>

                    <td>${pedido.fecha}</td>

                    <td>${pedido.estado}</td>

                    <td>$${pedido.total}</td>

                    <td>${pedido.idPago}</td>

                    <td>

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