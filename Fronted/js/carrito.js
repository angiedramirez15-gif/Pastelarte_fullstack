
function agregarAlCarrito(nombre, precio, imagen) {
  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  carrito.push({ nombre, precio, imagen });
  localStorage.setItem("carrito", JSON.stringify(carrito));
  alert(`${nombre} ha sido agregado al carrito 🛒`);
}


document.addEventListener("DOMContentLoaded", () => {
  const contenedor = document.getElementById("carrito-contenido");
  if (contenedor) {
    let carrito = JSON.parse(localStorage.getItem("carrito")) || [];

    if (carrito.length === 0) {
      contenedor.innerHTML = '<p class="carrito-vacio">Tu carrito está vacío 😢</p>';
    }
    else {
      let total = 0;
      contenedor.innerHTML = `
        <div class="lista-carrito">
          ${carrito.map(item => {
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
    </div>
  `;
      }).join("")}

        </div>
        <h3>Total: $${total.toLocaleString()}</h3>
      `;
    }
  }


  const btnVaciar = document.getElementById("vaciarCarrito");
  if (btnVaciar) {
    btnVaciar.addEventListener("click", () => {
      localStorage.removeItem("carrito");
      location.reload();
    });
  }
});

// ===============================
// PASTEL PERSONALIZADO → AL CARRITO
// ===============================
document.addEventListener("DOMContentLoaded", () => {

  const form = document.getElementById("formPersonalizar");

  if (form) {

    form.addEventListener("submit", function (e) {
      e.preventDefault();

      // CAPTURAR DATOS
      let sabor = document.getElementById("sabor").value;
      let tamano = document.getElementById("tamano").value;

      let decoraciones = Array.from(
        document.querySelectorAll(".checks input:checked")
      ).map(d => d.value);

      let mensaje = document.getElementById("mensaje").value.trim();
      let imagenInput = document.getElementById("imagen").files[0];

      // Precio base del pastel personalizado
      let precio = 55000;

      // Convertir imagen a base64 (si existe)
      if (imagenInput) {
        let reader = new FileReader();
        reader.onload = function () {
          let imagenBase64 = reader.result;
          guardarPastelPersonalizado(sabor, tamano, decoraciones, mensaje, imagenBase64, precio);
        };
        reader.readAsDataURL(imagenInput);
      } else {
        // Imagen por defecto
        guardarPastelPersonalizado(sabor, tamano, decoraciones, mensaje, "imagen/LOGO1.png", precio);
      }
    });
  }
});


// Guarda el pastel personalizado como un producto en el carrito
function guardarPastelPersonalizado(sabor, tamano, decoraciones, mensaje, imagen, precio) {

  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];

  carrito.push({
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

  // opcional: redirigir al carrito
  window.location.href = "carrito.html";
}

