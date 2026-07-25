document.addEventListener("DOMContentLoaded", function () {

  const nombre = localStorage.getItem("nombre");
  const idRol = localStorage.getItem("idRol");

  const btnLogin = document.querySelector(".btn-login");
  const userMenu = document.getElementById("userMenu");
  const nombreUsuario = document.getElementById("nombreUsuario");
  const linkPerfil = document.querySelector('#menuUsuario a[href="perfil.html"]');

  if (nombre) {

    if (btnLogin) btnLogin.style.display = "none";
    if (userMenu) userMenu.style.display = "flex";
    if (nombreUsuario) nombreUsuario.textContent = nombre;


    if (idRol === "5" && linkPerfil) {
      linkPerfil.textContent = "⚙️ Panel de administración";
      linkPerfil.href = "admin.html";
    }
  }

  // --- Link "Gestión" en el menú principal: solo visible para administradores logueados ---
  const linkGestion = document.getElementById("linkGestion");
  if (linkGestion) {
    linkGestion.style.display = (idRol === "5") ? "inline-block" : "none";
  }

  // --- Carrito: oculto para administradores, ya que no compran ---
  const linkCarrito = document.querySelector(".ver-carrito");
  if (linkCarrito && idRol === "5") {
    linkCarrito.style.display = "none";
  }

  // --- Menú desplegable ---
  const boton = document.getElementById("btnUsuario");
  const menu = document.getElementById("menuUsuario");

  if (boton && menu) {
    boton.addEventListener("click", function () {
      menu.classList.toggle("mostrar");
    });

    window.addEventListener("click", function (e) {
      if (!boton.contains(e.target) && !menu.contains(e.target)) {
        menu.classList.remove("mostrar");
      }
    });
  }

  // --- Cerrar sesión ---
  const salir = document.getElementById("cerrarSesion");

  if (salir) {
    salir.addEventListener("click", function (e) {
      e.preventDefault();
      localStorage.removeItem("clienteId");
      localStorage.removeItem("nombre");
      localStorage.removeItem("idRol");
      window.location.href = "login.html";
    });
  }
});