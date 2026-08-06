const API_PERSONALIZACIONES = "http://localhost:8080/personalizaciones";

const ESTADOS_PERSONALIZACION = {
    pendiente_cotizacion: "Pendiente de cotizar",
    cotizado: "Cotizado — esperando al cliente",
    aceptado: "Aceptado por el cliente"
};

async function cargarPersonalizaciones() {

    try {

        const respuesta = await fetch(API_PERSONALIZACIONES);
        const solicitudes = await respuesta.json();

        if (solicitudes.length === 0) {
            listaPersonalizaciones.innerHTML = "<p>No hay solicitudes de diseños personalizados todavía.</p>";
            return;
        }

        // Las pendientes de cotizar primero
        solicitudes.sort((a, b) => (a.estado === "pendiente_cotizacion" ? -1 : 1));

        listaPersonalizaciones.innerHTML = solicitudes.map((s) => `
            <div class="tarjeta-personalizacion">
                <div class="tarjeta-personalizacion-img">
                    ${s.imagen ? `<img src="${s.imagen}" alt="Referencia del cliente">` : "<p><small>Sin imagen de referencia</small></p>"}
                </div>
                <div class="tarjeta-personalizacion-info">
                    <p><strong>Cliente ID:</strong> ${s.idCliente ?? "—"}</p>
                    <p><strong>Sabor:</strong> ${s.sabor || "—"}</p>
                    <p><strong>Tamaño:</strong> ${s.tamano || "—"}</p>
                    <p><strong>Decoración:</strong> ${s.decoraciones || "—"}</p>
                    <p><strong>Notas del cliente:</strong> ${s.descripcion || "—"}</p>
                    <p><strong>Estado:</strong> ${ESTADOS_PERSONALIZACION[s.estado] || s.estado}</p>

                    ${s.estado === "pendiente_cotizacion" ? `
                        <div class="form-cotizar">
                            <input type="number" id="precio-${s.idPersonalizacion}" placeholder="Precio en $">
                            <button class="btn-editar" onclick="cotizarPersonalizacion(${s.idPersonalizacion})">Enviar cotización</button>
                        </div>
                    ` : `<p><strong>Precio cotizado:</strong> $${s.costoExtra ?? "—"}</p>`}
                </div>
            </div>
        `).join("");

    } catch (error) {
        console.error("Error al cargar las solicitudes personalizadas:", error);
    }
}

async function cotizarPersonalizacion(id) {

    const input = document.getElementById(`precio-${id}`);
    const precio = Number(input.value);

    if (!precio || precio <= 0) {
        alert("Escribe un precio válido antes de enviar la cotización.");
        return;
    }

    try {
        const respuesta = await fetch(`${API_PERSONALIZACIONES}/${id}/cotizar`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ costoExtra: precio })
        });

        if (!respuesta.ok) throw new Error(`El servidor respondió ${respuesta.status}`);

        alert("Cotización enviada. El cliente ya la puede ver en su perfil.");
        cargarPersonalizaciones();

    } catch (error) {
        console.error("Error al cotizar:", error);
        alert("No se pudo enviar la cotización: " + error.message);
    }
}
