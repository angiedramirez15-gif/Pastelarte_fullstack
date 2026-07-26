const API_MENSAJES = "http://localhost:8080/mensajes-contacto";

if (btnVerMensajes) {
    btnVerMensajes.addEventListener("click", cargarMensajes);
}

async function cargarMensajes() {

    try {

        listaProductos.style.display = "none";
        formularioProducto.style.display = "none";
        listaPedidos.style.display = "none";
        listaClientes.style.display = "none";
        listaMensajes.style.display = "block";

        const respuesta = await fetch(API_MENSAJES);
        const mensajes = await respuesta.json();

        if (mensajes.length === 0) {
            listaMensajes.innerHTML = "<p>No hay mensajes de contacto todavía.</p>";
            return;
        }

        let html = `
            <table class="tabla-productos">
                <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Nombre</th>
                        <th>Correo</th>
                        <th>Teléfono</th>
                        <th>Mensaje</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
        `;

        mensajes.forEach((m) => {

            const fecha = new Date(m.fecha).toLocaleString("es-CO");

            const botonLeido = m.leido
                ? "<span class=\"estado-pagado\">Leído</span>"
                : `<button onclick="marcarLeido(${m.idMensaje})" class="btn-editar">Marcar leído</button>`;

            html += `
                <tr>
                    <td>${fecha}</td>
                    <td>${m.nombre}</td>
                    <td>${m.correo}</td>
                    <td>${m.telefono}</td>
                    <td style="text-align:left; max-width:250px;">${m.mensaje}</td>
                    <td>${botonLeido}</td>
                    <td>
                        <button onclick="eliminarMensaje(${m.idMensaje})" class="btn-eliminar">Eliminar</button>
                    </td>
                </tr>
            `;
        });

        html += `
                </tbody>
            </table>
        `;

        listaMensajes.innerHTML = html;

    } catch (error) {
        console.error("Error al cargar mensajes:", error);
    }
}

async function marcarLeido(id) {

    try {
        await fetch(`${API_MENSAJES}/${id}/leido`, { method: "PUT" });
        cargarMensajes();
    } catch (error) {
        console.error("Error al marcar el mensaje como leído:", error);
    }
}

async function eliminarMensaje(id) {

    const confirmar = confirm("¿Eliminar este mensaje?");

    if (!confirmar) return;

    try {
        await fetch(`${API_MENSAJES}/${id}`, { method: "DELETE" });
        cargarMensajes();
    } catch (error) {
        console.error("Error al eliminar el mensaje:", error);
    }
}
