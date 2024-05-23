document.addEventListener("DOMContentLoaded", () => {
    showCategories()
});

async function showCategories() {
    try {
        const categoriesResponse = await fetch("http://localhost:8080/Burguer/controller/category/getall");
        const categoriesData = await categoriesResponse.json();

        const categoryFlex = document.querySelector("#categories > .flex");
        categoryFlex.innerHTML = ""; // Limpiar el contenedor antes de agregar elementos

        // Agregar la tarjeta para "All"
        const allCard = document.createElement("div");
        allCard.className = "card";
        allCard.innerHTML = `
        <div onclick="showBurguerSelling()" style="cursor: pointer;">
        <img src="./img/All.png" style="width: 100%; height: 80%;" alt="All">
        <p>All</p>
        </div>
    
        `;
        categoryFlex.appendChild(allCard);

        // Agregar tarjetas para las categorías obtenidas
        for (const category of categoriesData) {
            const card = document.createElement("div");
            card.className = "card";
            card.innerHTML = `
            <div onclick="showBurguerSelling(${category.id})" style="cursor: pointer;">
            <img src="${category.img}" style="width: 100%; height: 80%;" alt="Imagen de ${category.name}">
            <p>${category.name}</p>
            </div>
        
            `;
            categoryFlex.appendChild(card);
        }
    } catch (error) {
        console.error("Error al obtener categorías:", error);
    }
}

// Llamar a la función para mostrar las categorías
showCategories();
