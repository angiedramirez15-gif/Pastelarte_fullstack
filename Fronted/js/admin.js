// PRODUCTOS
const listaProductos = document.getElementById("listaProductos");
const btnVerProductos = document.getElementById("btnVerProductos");
const btnAgregar = document.getElementById("btnAgregar");
const formularioProducto = document.getElementById("formularioProducto");
const guardarProducto = document.getElementById("guardarProducto");

// PEDIDOS
const listaPedidos = document.getElementById("listaPedidos");
const btnVerPedidos = document.getElementById("btnVerPedidos");

// CLIENTES
const listaClientes = document.getElementById("listaClientes");
const btnVerClientes = document.getElementById("btnVerClientes");

// MENSAJES DE CONTACTO
const listaMensajes = document.getElementById("listaMensajes");
const btnVerMensajes = document.getElementById("btnVerMensajes");

// VARIABLE GLOBAL EDITAR
let idEditando = null;


// Lista de todas las vistas dinámicas
const secciones = [
  listaProductos,
  formularioProducto,
  listaPedidos,
  listaClientes,
  listaMensajes
];

// Función para ocultar todas las vistas y mostrar solo la seleccionada
function cambiarVista(seccionVisible) {
  secciones.forEach(seccion => {
    if (seccion) seccion.style.display = "none";
  });

  if (seccionVisible) {
    seccionVisible.style.display = "block";
  }
}

// Eventos de los botones para alternar las vistas
if (btnVerProductos) {
  btnVerProductos.addEventListener("click", () => {
    cambiarVista(listaProductos);
  });
}

if (btnAgregar) {
  btnAgregar.addEventListener("click", () => {
    idEditando = null; // Reinicia el estado por si estabas editando
    cambiarVista(formularioProducto);
  });
}

if (btnVerPedidos) {
  btnVerPedidos.addEventListener("click", () => {
    cambiarVista(listaPedidos);
  });
}

if (btnVerClientes) {
  btnVerClientes.addEventListener("click", () => {
    cambiarVista(listaClientes);
  });
}

if (btnVerMensajes) {
  btnVerMensajes.addEventListener("click", () => {
    cambiarVista(listaMensajes);
  });
}