const API_URL = "http://localhost:8080/productos";

let productosCache = [];
let fotoBase64Actual = null;
let imagenActualEditando = null;

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

    document.getElementById("categoriaProducto").value = "";

    document.getElementById("fotoProducto").value = "";

    fotoBase64Actual = null;

    imagenActualEditando = null;

    document.getElementById("previewFoto").style.display = "none";
});

// VISTA PREVIA DE LA FOTO AL ELEGIRLA

document.getElementById("fotoProducto").addEventListener("change", (e) => {

    const archivo = e.target.files[0];

    if (!archivo) return;

    const lector = new FileReader();

    lector.onload = () => {
        fotoBase64Actual = lector.result;
        const preview = document.getElementById("previewFoto");
        preview.src = fotoBase64Actual;
        preview.style.display = "block";
    };

    lector.readAsDataURL(archivo);
});

// CARGAR PRODUCTOS

async function cargarProductos() {

    try {

        listaProductos.style.display = "block";

        listaPedidos.style.display = "none";

        formularioProducto.style.display = "none";

        const respuesta = await fetch(API_URL);

        const productos = await respuesta.json();

        productosCache = productos;

        let html = `
            <table class="tabla-productos">

                <thead>

                    <tr>
                        <th>ID</th>
                        <th>Foto</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Categoría</th>
                        <th>Precio</th>
                        <th>Acciones</th>
                    </tr>

                </thead>

                <tbody>
        `;

        productos.forEach((producto) => {

            const fotoHtml = producto.imagen
                ? `<img src="${producto.imagen}" alt="${producto.nombre}" style="max-width:50px; border-radius:6px;">`
                : "—";

            html += `
                <tr>

                    <td>${producto.idProducto}</td>

                    <td>${fotoHtml}</td>

                    <td>${producto.nombre}</td>

                    <td>${producto.descripcion}</td>

                    <td>${producto.categoria || "—"}</td>

                    <td>$${producto.precio}</td>

                    <td>

                        <button
                            onclick="editarProducto(${producto.idProducto})"
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

function editarProducto(id) {

    const producto = productosCache.find(p => p.idProducto === id);

    if (!producto) return;

    formularioProducto.style.display = "block";

    listaProductos.style.display = "none";

    listaPedidos.style.display = "none";

    document.getElementById("tituloFormulario").textContent =
        "Editando producto";

    document.getElementById("nombreProducto").value = producto.nombre;

    document.getElementById("descripcionProducto").value = producto.descripcion;

    document.getElementById("precioProducto").value = producto.precio;

    document.getElementById("categoriaProducto").value = producto.categoria || "";

    document.getElementById("fotoProducto").value = "";

    fotoBase64Actual = null;

    imagenActualEditando = producto.imagen || null;

    const preview = document.getElementById("previewFoto");

    if (producto.imagen) {
        preview.src = producto.imagen;
        preview.style.display = "block";
    } else {
        preview.style.display = "none";
    }

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

    const categoria =
        document.getElementById("categoriaProducto").value;

    // Si eligió una foto nueva se usa esa; si está editando y no cambió la foto, se conserva la que ya tenía
    const imagen = fotoBase64Actual || imagenActualEditando || null;

    const nuevoProducto = {
        nombre,
        descripcion,
        precio,
        categoria,
        imagen
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