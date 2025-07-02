// Funciones específicas para el manejo de pedidos
document.addEventListener('DOMContentLoaded', function() {
    // Actualizar estado del pedido
    const statusSelects = document.querySelectorAll('.status-select');
    statusSelects.forEach(select => {
        select.addEventListener('change', function() {
            const pedidoId = this.dataset.pedidoId;
            const estadoId = this.value;
            
            fetch(`${pedidoId}/estado`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ estadoId: estadoId })
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    location.reload();
                } else {
                    alert('Error al actualizar el estado');
                }
            });
        });
    });
    
    // Calcular total del pedido
    const cantidadInputs = document.querySelectorAll('.cantidad-input');
    const precioElements = document.querySelectorAll('.precio-unitario');
    
    function calcularTotal() {
        let total = 0;
        
        cantidadInputs.forEach((input, index) => {
            const cantidad = parseInt(input.value) || 0;
            const precio = parseFloat(precioElements[index].textContent) || 0;
            total += cantidad * precio;
        });
        
        document.getElementById('total-pedido').textContent = total.toFixed(2);
    }
    
    cantidadInputs.forEach(input => {
        input.addEventListener('change', calcularTotal);
        input.addEventListener('keyup', calcularTotal);
    });
});