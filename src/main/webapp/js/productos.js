// Funciones específicas para el manejo de productos
document.addEventListener('DOMContentLoaded', function() {
    // Filtrado de productos por categoría
    const categoryButtons = document.querySelectorAll('.category-btn');
    const productCards = document.querySelectorAll('.producto-card');
    
    categoryButtons.forEach(button => {
        button.addEventListener('click', function() {
            const categoryId = this.dataset.categoryId;
            
            // Actualizar botón activo
            categoryButtons.forEach(btn => btn.classList.remove('active'));
            this.classList.add('active');
            
            // Filtrar productos
            productCards.forEach(card => {
                const cardCategory = card.dataset.categoryId;
                if (categoryId === 'all' || cardCategory === categoryId) {
                    card.style.display = 'block';
                } else {
                    card.style.display = 'none';
                }
            });
        });
    });
    
    // Personalización de productos
    const personalizacionForms = document.querySelectorAll('.personalizacion-form');
    personalizacionForms.forEach(form => {
        form.addEventListener('change', function() {
            const precioBase = parseFloat(this.dataset.precioBase);
            let precioExtra = 0;
            
            this.querySelectorAll('select, input[type="radio"]:checked').forEach(input => {
                precioExtra += parseFloat(input.dataset.precioExtra || 0);
            });
            
            const precioTotal = precioBase + precioExtra;
            this.querySelector('.precio-total').textContent = precioTotal.toFixed(2);
        });
    });
});