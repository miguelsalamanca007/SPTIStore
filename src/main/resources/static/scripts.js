document.addEventListener('DOMContentLoaded', function () {
    const searchBtn = document.getElementById('searchBtn');
    const searchInput = document.getElementById('search');
    const productsDiv = document.getElementById('products');

    function fetchProducts(url) {
        fetch(url)
            .then(response => response.json())
            .then(products => {
                productsDiv.innerHTML = '';
                products.forEach(product => {
                    const productDiv = document.createElement('div');
                    productDiv.classList.add('product');
                    productDiv.innerHTML = `
                <h2>${product.name}</h2>
                <img src="${product.image_url}" alt="${product.name}" width="200" height="200">
                <p>Precio: $${product.price}</p>
                <button class="addToCartBtn">Agregar a la cesta</button>
                <button class="buyBtn">Comprar</button>
            `;
                    productsDiv.appendChild(productDiv);
                });
            });
    }

    fetchProducts('http://localhost:8080/api/products');

    searchBtn.addEventListener('click', () => {
        const searchTerm = searchInput.value.trim();
        if (searchTerm) {
            fetchProducts(`http://localhost:8080/api/productsByName/${searchTerm}`);
        } else {
            fetchProducts('http://localhost:8080/api/products');
        }
    });
});
