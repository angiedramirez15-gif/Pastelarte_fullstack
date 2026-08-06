// Carga los productos de una categoría desde la API y los pinta como tarjetas.
// Se usa en pasteles.html, ceroazucar.html, adicionales.html y clientes.html (galería).

const API_PRODUCTOS = "http://localhost:8080/productos";

// Interpreta el campo "porciones" como opciones de tamaño con incremento de precio,
// si tiene el formato "Chico:0,Mediano:15000,Grande:30000". Si no tiene ese formato
// (es solo texto descriptivo normal, como "8-10 porciones"), devuelve null.
function parseOpcionesTamano(porciones) {
    if (!porciones || !porciones.includes(":")) return null;

    const opciones = porciones.split(",").map(par => {
        const [tamano, incremento] = par.split(":");
        const numero = Number(incremento);
        if (!tamano || Number.isNaN(numero)) return null;
        return { tamano: tamano.trim(), incremento: numero };
    });

    return opciones.every(Boolean) ? opciones : null;
}

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

            const opcionesTamano = parseOpcionesTamano(producto.porciones);

            const card = document.createElement("div");
            card.className = "card";

            const porcionesHtml = (!opcionesTamano && producto.porciones)
                ? `<p class="porciones-producto">🍰 ${producto.porciones}</p>`
                : "";

            // Si hay tamaños, el botón abre un menú de opciones en vez de agregar directo
            const botonHtml = opcionesTamano
                ? `<button class="btn-carrito btn-elegir-tamano">Elegir porciones</button>`
                : `<button class="btn-carrito">Agregar al carrito</button>`;

            const menuTamanoHtml = opcionesTamano
                ? `
                    <div class="menu-tamanos" style="display:none;">
                        ${opcionesTamano.map((op, i) => `
                            <button class="opcion-tamano" data-indice="${i}">
                                <span>${op.tamano}</span>
                                <span>$${(producto.precio + op.incremento).toLocaleString("es-CO")}</span>
                            </button>
                        `).join("")}
                    </div>
                  `
                : "";

            const precioHtml = opcionesTamano
                ? `<span class="precio">Desde $${producto.precio.toLocaleString("es-CO")}</span>`
                : `<span class="precio">$${producto.precio.toLocaleString("es-CO")}</span>`;

            card.innerHTML = `
                <img src="${producto.imagen || "imagen/LOGO1.png"}" alt="${producto.nombre}">
                <h3>${producto.nombre}</h3>
                <p>${producto.descripcion || ""}</p>
                ${porcionesHtml}
                ${precioHtml}
                ${botonHtml}
                ${menuTamanoHtml}
            `;

            if (opcionesTamano) {

                const boton = card.querySelector(".btn-elegir-tamano");
                const menu = card.querySelector(".menu-tamanos");

                // Abre/cierra el menú de tamaños
                boton.addEventListener("click", () => {
                    menu.style.display = menu.style.display === "none" ? "flex" : "none";
                });

                // Al elegir un tamaño, se agrega ese al carrito y se cierra el menú
                menu.querySelectorAll(".opcion-tamano").forEach((btnOpcion) => {
                    btnOpcion.addEventListener("click", () => {
                        const opcion = opcionesTamano[btnOpcion.dataset.indice];
                        agregarAlCarrito(
                            producto.idProducto,
                            `${producto.nombre} (${opcion.tamano})`,
                            producto.precio + opcion.incremento,
                            producto.imagen
                        );
                        menu.style.display = "none";
                    });
                });

            } else {

                // Producto normal, sin tamaños: se agrega directo como antes
                card.querySelector(".btn-carrito").addEventListener("click", () => {
                    agregarAlCarrito(
                        producto.idProducto,
                        producto.nombre,
                        producto.precio,
                        producto.imagen
                    );
                });
            }

            contenedor.appendChild(card);
        });

    } catch (error) {
        console.error("Error al cargar el catálogo:", error);
        contenedor.innerHTML = "<p>No se pudo cargar el catálogo. Intenta de nuevo más tarde.</p>";
    }
}
