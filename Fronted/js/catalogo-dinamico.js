// Carga los productos de una categoría desde la API y los pinta como tarjetas.
// Se usa en pasteles.html, ceroazucar.html y adicionales.html.

const API_PRODUCTOS = "http://localhost:8080/productos";

async function cargarCatalogo(categoria, idContenedor) {

    const contenedor = document.getElementById(idContenedor);

    try {

        const respuesta = await fetch(`${API_PRODUCTOS}/categoria/${categoria}`);
        const productos = await respuesta.json();

        if (productos.length === 0) {
            contenedor.innerHTML = "<p>Todavía no hay productos en esta categoría.</p>";
            return;
        }

        contenedor.innerHTML = "";

        productos.forEach((producto) => {

            const card = document.createElement("div");
            card.className = "card";

            card.innerHTML = `
                <img src="${producto.imagen || "imagen/LOGO1.png"}" alt="${producto.nombre}">
                <h3>${producto.nombre}</h3>
                <p>${producto.descripcion || ""}</p>
                <span class="precio">$${producto.precio.toLocaleString("es-CO")}</span>
                <button class="btn-carrito">Agregar al carrito</button>
            `;

            // Se agrega el evento por código (no en el HTML) para evitar
            // problemas si el nombre del producto tiene comillas o tildes.
            card.querySelector(".btn-carrito").addEventListener("click", () => {
                agregarAlCarrito(
                    producto.idProducto,
                    producto.nombre,
                    producto.precio,
                    producto.imagen
                );
            });

            contenedor.appendChild(card);
        });

    } catch (error) {
        console.error("Error al cargar el catálogo:", error);
        contenedor.innerHTML = "<p>No se pudo cargar el catálogo. Intenta de nuevo más tarde.</p>";
    }
}
