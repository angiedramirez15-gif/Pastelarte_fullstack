const API_URL = "http://localhost:8080/productos";

// EVENTOS

btnVerProductos.addEventListener(
    "click",
    cargarProductos
);

// MOSTRAR FORMULARIO

btnAgregar.addEventListener("click", () => {

    formularioProducto.style.display = "block";

    listaProductos.style.display = "none";

    listaPedidos.style.display = "none";

    document.getElementById("tituloFormulario").textContent =
        "Agregar nuevo producto";

    idEditando = null;

    document.getElementById("nombreProducto").value = "";

    document.getElementById("descripcionProducto").value = "";

    document.getElementById("precioProducto").value = "";
});

// CARGAR PRODUCTOS

async function cargarProductos() {

    try {

        listaProductos.style.display = "block";

        listaPedidos.style.display = "none";

        formularioProducto.style.display = "none";

        const respuesta = await fetch(API_URL);

        const productos = await respuesta.json();

        let html = `
            <table class="tabla-productos">

                <thead>

                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Precio</th>
                        <th>Acciones</th>
                    </tr>

                </thead>

                <tbody>
        `;

        productos.forEach((producto) => {

            html += `
                <tr>

                    <td>${producto.idProducto}</td>

                    <td>${producto.nombre}</td>

                    <td>${producto.descripcion}</td>

                    <td>$${producto.precio}</td>

                    <td>

                        <button
                            onclick="editarProducto(
                                ${producto.idProducto},
                                '${producto.nombre}',
                                '${producto.descripcion}',
                                ${producto.precio}
                            )"
                            class="btn-editar"
                        >
                            Editar
                        </button>

                        <button
                            onclick="eliminarProducto(${producto.idProducto})"
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

        listaProductos.innerHTML = html;

    } catch (error) {

        console.error(
            "Error al cargar productos:",
            error
        );
    }
}

// ELIMINAR PRODUCTO

async function eliminarProducto(id) {

    const confirmar =
        confirm("¿Deseas eliminar este producto?");

    if (!confirmar) return;

    try {

        await fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        });

        alert("Producto eliminado correctamente");

        cargarProductos();

    } catch (error) {

        console.error(
            "Error al eliminar producto:",
            error
        );
    }
}

// EDITAR PRODUCTO

function editarProducto(id, nombre, descripcion, precio) {

    formularioProducto.style.display = "block";

    listaProductos.style.display = "none";

    listaPedidos.style.display = "none";

    document.getElementById("tituloFormulario").textContent =
        "Editando producto";

    document.getElementById("nombreProducto").value =
        nombre;

    document.getElementById("descripcionProducto").value =
        descripcion;

    document.getElementById("precioProducto").value =
        precio;

    idEditando = id;
}

// GUARDAR PRODUCTO

guardarProducto.addEventListener("click", async () => {

    const nombre =
        document.getElementById("nombreProducto").value;

    const descripcion =
        document.getElementById("descripcionProducto").value;

    const precio =
        document.getElementById("precioProducto").value;

    const nuevoProducto = {
        nombre,
        descripcion,
        precio
    };

    try {

        if (idEditando) {

            await fetch(`${API_URL}/${idEditando}`, {

                method: "PUT",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(nuevoProducto)
            });

            alert("Producto actualizado correctamente");

            idEditando = null;

        } else {

            await fetch(API_URL, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(nuevoProducto)
            });

            alert("Producto agregado correctamente");
        }

        formularioProducto.style.display = "none";

        cargarProductos();

    } catch (error) {

        console.error(
            "Error al guardar producto:",
            error
        );
    }
});