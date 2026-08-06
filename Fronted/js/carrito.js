// ==========================================
// 1. FUNCIONES BASE DEL CARRITO
// ==========================================

function agregarAlCarrito(idProducto, nombre, precio, imagen) {
  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  carrito.push({ idProducto, nombre, precio, imagen });
  localStorage.setItem("carrito", JSON.stringify(carrito));
  alert(`${nombre} ha sido agregado al carrito 🛒`);
}

function renderCarrito() {
  const contenedor = document.getElementById("carrito-contenido");
  if (!contenedor) return;

  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];

  if (carrito.length === 0) {
    contenedor.innerHTML = '<p class="carrito-vacio">Tu carrito está vacío 😢</p>';
  } else {
    let total = 0;
    contenedor.innerHTML = `
        <div class="lista-carrito">
          ${carrito.map((item, index) => {
        total += item.precio;

        let extras = "";
        if (item.nombre === "Pastel Personalizado" && item.detalles) {
          extras = `
                <div class="extras">
                  <p><strong>Sabor:</strong> ${item.detalles.sabor}</p>
                  <p><strong>Tamaño:</strong> ${item.detalles.tamano}</p>
                  <p><strong>Decoraciones:</strong> ${item.detalles.decoraciones.join(", ") || "Ninguna"}</p>
                  <p><strong>Mensaje:</strong> ${item.detalles.mensaje || "Sin mensaje"}</p>
                </div>
              `;
        }

        return `
              <div class="item-carrito">
                <img src="${item.imagen}" alt="${item.nombre}">
                <div class="info">
                  <p><strong>${item.nombre}</strong></p>
                  <p>Precio: $${item.precio.toLocaleString()}</p>
                  ${extras}
                </div>
                <button class="btn-quitar-item" onclick="eliminarDelCarrito(${index})" title="Quitar del carrito">✕</button>
              </div>
            `;
      }).join("")}
        </div>
        <h3>Total: $${total.toLocaleString()}</h3>
      `;
  }
}

// Quita un solo producto del carrito (por su posición en la lista) y refresca la vista
function eliminarDelCarrito(index) {
  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  carrito.splice(index, 1);
  localStorage.setItem("carrito", JSON.stringify(carrito));
  renderCarrito();
}

document.addEventListener("DOMContentLoaded", () => {
  renderCarrito();

  const btnVaciar = document.getElementById("vaciarCarrito");
  if (btnVaciar) {
    btnVaciar.addEventListener("click", () => {
      localStorage.removeItem("carrito");
      location.reload();
    });
  }
});


// ==========================================
// 2. PASTEL PERSONALIZADO (SIN BASE64)
// ==========================================

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("formPersonalizar");

  if (form) {
    form.addEventListener("submit", function (e) {
      e.preventDefault();

      let sabor = document.getElementById("sabor").value;
      let tamano = document.getElementById("tamano").value;
      let decoraciones = Array.from(
          document.querySelectorAll(".checks input:checked")
      ).map(d => d.value);

      let mensaje = document.getElementById("mensaje").value.trim();
      let precio = 55000;

      // Usamos una ruta de imagen estática para evitar guardar Base64 pesado en el localStorage
      guardarPastelPersonalizado(sabor, tamano, decoraciones, mensaje, "imagen/LOGO1.png", precio);
    });
  }
});

function guardarPastelPersonalizado(sabor, tamano, decoraciones, mensaje, imagen, precio) {
  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];

  carrito.push({
    idProducto: 27, // ID asignado al pastel personalizado
    nombre: "Pastel Personalizado",
    precio: precio,
    imagen: imagen,
    detalles: {
      sabor,
      tamano,
      decoraciones,
      mensaje
    }
  });

  localStorage.setItem("carrito", JSON.stringify(carrito));
  alert("Tu diseño ha sido enviado al carrito 🛒");
  window.location.href = "carrito.html";
}


// ==========================================
// 3. ENVÍO DE PEDIDO MULTIPART A SPRING BOOT
// ==========================================

async function procesarPedidoFinal(idInputComprobante = "imagen", idInputNequi = "numeroNequi") {
  const clienteId = localStorage.getItem("clienteId");
  const carrito = JSON.parse(localStorage.getItem("carrito")) || [];

  if (!clienteId) {
    alert("Debes iniciar sesión para realizar un pedido.");
    window.location.href = "login.html";
    return;
  }

  if (carrito.length === 0) {
    alert("El carrito está vacío.");
    return;
  }

  const totalPedido = carrito.reduce((sum, item) => sum + item.precio, 0);

  const detallesDTO = carrito.map(item => ({
    idProducto: item.idProducto,
    cantidad: 1,
    subtotal: item.precio
  }));

  const inputNequi = document.getElementById(idInputNequi);
  const pedidoData = {
    idCliente: parseInt(clienteId),
    fecha: new Date().toISOString().split("T")[0],
    estado: "pendiente",
    total: totalPedido,
    idPago: 1,
    numeroNequi: inputNequi ? inputNequi.value : "",
    detalles: detallesDTO
  };

  const formData = new FormData();
  formData.append("pedido", JSON.stringify(pedidoData));

  const archivoInput = document.getElementById(idInputComprobante);
  if (archivoInput && archivoInput.files[0]) {
    formData.append("comprobante", archivoInput.files[0]);
  }

  try {
    const respuesta = await fetch("http://localhost:8080/pedidos/con-comprobante", {
      method: "POST",
      body: formData
    });

    if (respuesta.ok) {
      alert("✅ Pedido realizado exitosamente.");
      localStorage.removeItem("carrito");
      window.location.href = "pedidos.html";
    } else {
      const errorText = await respuesta.text();
      alert("❌ Error al realizar el pedido: " + errorText);
    }
  } catch (error) {
    console.error("Error al conectar con la API:", error);
    alert("Error de conexión con el servidor.");
  }
}

// Event listener global para capturar la finalización del pedido
document.addEventListener("DOMContentLoaded", () => {
  const formPago = document.getElementById("formFinalizarPedido") || document.getElementById("formCheckout");
  if (formPago) {
    formPago.addEventListener("submit", (e) => {
      e.preventDefault();
      procesarPedidoFinal();
    });
  }
});