console.log("JS conectado"); // prueba

function mostrarRegistro() {
  document.getElementById("loginSection").style.display = "none";
  document.getElementById("registroSection").style.display = "block";
}

function mostrarLogin() {
  document.getElementById("loginSection").style.display = "block";
  document.getElementById("registroSection").style.display = "none";
}

// REGISTRAR
function registrar() {

  const nombre = document.getElementById("nuevoNombre").value;
  const correo = document.getElementById("nuevoCorreo").value;
  const clave = document.getElementById("nuevaClave").value;
  const confirmar = document.getElementById("confirmarClave").value;
  const direccion = document.getElementById("direccion").value;

  if (!nombre || !correo || !clave || !confirmar || !direccion) {
    alert("⚠️ Completa todos los campos");
    return;
  }

  if (clave !== confirmar) {
    alert("❌ Las contraseñas no coinciden");
    return;
  }

  const data = {
    nombre: nombre,
    correo: correo,
    contrasena: clave,
    direccion: direccion,
    idRol: 6
  };

  console.log("📦 ENVIANDO:", data);

  fetch("http://localhost:8080/clientes", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  })
  .then(res => {
    if (!res.ok) throw new Error("Error " + res.status);
    return res.json();
  })
  .then(() => {
    alert("✅ Usuario registrado");
    mostrarLogin();
  })
  .catch(err => console.error("❌ ERROR:", err));
}
// LOGIN
function iniciarSesion() {
  const correo = document.getElementById("usuario").value;
  const clave = document.getElementById("clave").value;

  fetch("http://localhost:8080/clientes")
    .then(res => res.json())
    .then(data => {

      const usuario = data.find(u =>
        u.correo === correo && u.contrasena === clave
      );

      if (!usuario) {
        alert("Usuario incorrecto");
        return;
      }

      alert("Bienvenido " + usuario.nombre);

        localStorage.setItem("clienteId", usuario.idCliente);
        localStorage.setItem("nombre", usuario.nombre);

      if (usuario.idRol === 5) {
        window.location.href = "admin.html";
      } else {
        window.location.href = "index.html";
      }

    })
    .catch(err => console.error(err));
}
