const API_DASHBOARD = "http://localhost:8080/estadisticas/dashboard";

async function cargarDashboard() {

    const contenedor = document.getElementById("dashboard");
    if (!contenedor) return;

    try {

        const respuesta = await fetch(API_DASHBOARD);
        const datos = await respuesta.json();

        contenedor.innerHTML = `
            <div class="dashboard-cards">
                <div class="dashboard-card">
                    <h4>🏆 Producto más pedido este mes</h4>
                    <p>${datos.productoMasVendidoMes.nombre} (${datos.productoMasVendidoMes.cantidad} unidades)</p>
                </div>
                <div class="dashboard-card">
                    <h4>💵 Pendientes pago en efectivo</h4>
                    <p>${datos.pendientesEfectivo}</p>
                </div>
                <div class="dashboard-card">
                    <h4>💜 Comprobantes Nequi por revisar</h4>
                    <p>${datos.pendientesRevisionNequi}</p>
                </div>
                <div class="dashboard-card">
                    <h4>✅ Pedidos pagados</h4>
                    <p>${datos.pedidosPagados}</p>
                </div>
            </div>
        `;

    } catch (error) {
        console.error("Error al cargar el dashboard:", error);
    }
}

// Solo carga el dashboard si el usuario logueado es administrador
if (localStorage.getItem("idRol") === "5") {
    document.addEventListener("DOMContentLoaded", cargarDashboard);
}