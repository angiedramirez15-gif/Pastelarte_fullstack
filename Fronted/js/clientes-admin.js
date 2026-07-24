const API_CLIENTES = "http://localhost:8080/clientes";

if (btnVerClientes) {
    btnVerClientes.addEventListener("click", cargarClientes);
}

async function cargarClientes() {

    try {

        listaProductos.style.display = "none";
        formularioProducto.style.display = "none";
        listaPedidos.style.display = "none";
        listaClientes.style.display = "block";

        const respuesta = await fetch(API_CLIENTES);
        const clientes = await respuesta.json();

        // Solo mostramos clientes (idRol 6), no administradores
        const soloClientes = clientes.filter(c => c.idRol !== 5);

        let html = `
            <table class="tabla-productos">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Correo</th>
                        <th>Dirección</th>
                    </tr>
                </thead>
                <tbody>
        `;

        soloClientes.forEach((cliente) => {
            html += `
                <tr>
                    <td>${cliente.idCliente}</td>
                    <td>${cliente.nombre}</td>
                    <td>${cliente.correo}</td>
                    <td>${cliente.direccion}</td>
                </tr>
            `;
        });

        html += `
                </tbody>
            </table>
        `;

        listaClientes.innerHTML = html;

    } catch (error) {
        console.error("Error al cargar clientes:", error);
    }
}