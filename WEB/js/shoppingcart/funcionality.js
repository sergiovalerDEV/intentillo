document.addEventListener("DOMContentLoaded", () => {
    //loadItems();
});
/*async function loadItems(){
    const items = await fetch("http://localhost:8080/Burguer/controller/shoppingcartitem/getbyshoppingcart?SHOPPINGCART=" + userShoppingcart);
    const itemsJson = await items.json();
    
    const shoppingcartFlex = document.querySelector("#shoppingcart > .flex")
    shoppingcartFlex.innerHTML = ``;

    for(const item of itemsJson){
        //console.log("1")
        const itemInfo = await fetch("http://localhost:8080/Burguer/controller/item/getbyid?ID=" + item.item);
        const itemInfoJson = await itemInfo.json();
        console.log(itemInfoJson)

        const card = document.createElement("div");

        card.className = "item";

        card.innerHTML = `
        <div class="commonInfo">
            <img src="${itemInfoJson[0].image}">
            <p>${itemInfoJson[0].name}</p>
        </div>
        <div class="priceInfo">
            <p>${item.ammount * itemInfoJson[0].price}</p>
            <div class="buttons">
                <button onclick="increaseAmount(${item.item}, ${item.ammount})">+</button>
                <input type="text" value="${item.ammount}" onblur="updateAmount(${item.item}, value)">
                <button onclick="decreaseAmount(${item.item}, ${item.ammount})">-</button>
                <button onclick="renderProductExtras(${item.id})">X</button>
            </div>
        </div>
        `;

        shoppingcartFlex.appendChild(card);
    }
}
async function renderProductExtras(itemId) {
    const response = await fetch(`http://localhost:8080/Burguer/controller/shoppingcartitemextra/getbyshoppingcartitem?SHOPPINGCARTITEM=${itemId}`);
    const items = await response.json();
    
    const itemsContainer = document.querySelector('#ticketsWindow > .flex');
    itemsContainer.innerHTML = ''; 

    for (const item of items) {
        const itemDiv = document.createElement('div');
        itemDiv.classList.add('item');
        itemsContainer.appendChild(itemDiv);

        const itemDetailsResponse = await fetch(`http://localhost:8080/Burguer/controller/extra/getbyid?ID=${item.extra}`);
        const itemDetails = await itemDetailsResponse.json();
        const itemDetail = itemDetails[0]; 

        itemDiv.innerHTML = `
            <p>Nombre del extra: ${itemDetail.name}</p> 
            <p>Cantidad: ${item.ammount}</p>
        `;
    }
}*/

async function loadItems() {
    console.log("Cargando items...");
    const items = await fetch("http://localhost:8080/Burguer/controller/shoppingcartitem/getbyshoppingcart?SHOPPINGCART=" + userShoppingcart);
    const itemsJson = await items.json();
    console.log("Items cargados:", itemsJson);

    const shoppingcartFlex = document.querySelector("#shoppingcart > .flex")
    shoppingcartFlex.innerHTML = ``;

    for(const item of itemsJson) {
        console.log("Procesando item:", item);
        const itemInfo = await fetch("http://localhost:8080/Burguer/controller/item/getbyid?ID=" + item.item);
        const itemInfoJson = await itemInfo.json();
        console.log("Detalles del item:", itemInfoJson);

        const card = document.createElement("div");
        card.className = "item";

        card.innerHTML = `
            <div class="commonInfo">
                <img src="${itemInfoJson[0].image}">
                <p>${itemInfoJson[0].name}</p>
            </div>
            <div class="priceInfo">
                <p>${item.ammount * itemInfoJson[0].price}</p>
                <div class="buttons">
                    <button onclick="increaseAmount(${item.item}, ${item.ammount})">+</button>
                    <input type="text" value="${item.ammount}" onblur="updateAmount(${item.item}, value)">
                    <button onclick="decreaseAmount(${item.item}, ${item.ammount})">-</button>
                    <button onclick="toggleExtras(${item.item})">Ver Extras</button>
                </div>
            </div>
            <div id="extras-${item.item}" class="extras-container" style="display: none;"></div>
        `;

        shoppingcartFlex.appendChild(card);

        // Llamamos a la función renderProductExtras para cargar los extras del artículo
        console.log("Cargando extras para el item:", item.item);
        await renderProductExtras(item.item);
        console.log("Extras cargados para el item:", item.item);
    }

    console.log("Todos los items procesados.");
}

async function renderProductExtras(itemId) {
    console.log("Cargando extras para el item:", itemId);
    const response = await fetch(`http://localhost:8080/Burguer/controller/shoppingcartitemextra/getbyshoppingcartitem?SHOPPINGCARTITEM=${itemId}`);
    const items = await response.json();
    console.log("Extras cargados para el item:", itemId, items);
    
    const extrasContainer = document.querySelector(`#extras-${itemId}`);
    extrasContainer.innerHTML = ''; 

    for (const item of items) {
        const itemDetailsResponse = await fetch(`http://localhost:8080/Burguer/controller/extra/getbyid?ID=${item.extra}`);
        const itemDetails = await itemDetailsResponse.json();
        const itemDetail = itemDetails[0]; 

        const itemDiv = document.createElement('div');
        itemDiv.classList.add('extra');
        itemDiv.innerHTML = `
            <p>Nombre del extra: ${itemDetail.name}</p> 
            <p>Cantidad: ${item.ammount}</p>
        `;
        extrasContainer.appendChild(itemDiv);
    }

    console.log("Todos los extras procesados para el item:", itemId);
}



function toggleExtras(itemId) {
    const extrasContainer = document.querySelector(`#extras-${itemId}`);
    extrasContainer.style.display = extrasContainer.style.display === 'none' ? 'block' : 'none';
}

function buyShoppingcart(){
    fetch("http://localhost:8080/Burguer/controller/shoppingcart/add?USER=" + userId)
}

async function closeShoppingcart(){
    await fetch("http://localhost:8080/Burguer/controller/shoppingcart/update?ID=" + userShoppingcart + "&PRICE=price&OPENED=false")
    //await setProperties(userName);
    loadItems();
}

async function updateAmount(item, amount) {
    const updatedAmount = amount;
    await fetch("http://localhost:8080/Burguer/controller/shoppingcartitem/update?SHOPPINGCART=" + userShoppingcart + "&ITEM=" + item + "&AMMOUNT=" + updatedAmount);
    
    loadItems();
}

async function increaseAmount(item, amount) {
    console.log("12")
    const updatedAmount = amount + 1;
    await fetch("http://localhost:8080/Burguer/controller/shoppingcartitem/update?SHOPPINGCART=" + userShoppingcart + "&ITEM=" + item + "&AMMOUNT=" + updatedAmount);
    
    loadItems();
}

async function decreaseAmount(item, amount) {
    const updatedAmount = amount -1;
    await fetch("http://localhost:8080/Burguer/controller/shoppingcartitem/update?SHOPPINGCART=" + userShoppingcart + "&ITEM=" + item + "&AMMOUNT=" + updatedAmount);

    loadItems();
}



/*
<div class="commonInfo">
    <img src="${itemInfoJson.img}">
    <p>${itemInfoJson.name}</p>
</div>
<div class="priceInfo">
    <p>${item.price}</p>
    <div class="buttons">
        <button>+</button>
        <input type="text" value="${item.ammount}">
        <button>-</button>
    </div>
</div>*/