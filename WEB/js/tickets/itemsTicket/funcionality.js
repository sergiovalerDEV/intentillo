async function showItemsShoppingcart(shoppingcart) {
        const itemsShoppingcart = await fetch("http://localhost:8080/Burguer/controller/shoppingcartitem/getbyshoppingcart?SHOPPINGCART=" + shoppingcart);
        const itemsShoppingcartJson = await itemsShoppingcart.json();
        
        const itemsTicketFlex = document.querySelector("#itemsTicketWindow > .flex");

        itemsTicketFlex.innerHTML = ``;

        for(const item of itemsShoppingcartJson){
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
                <p>${item.ammount * itemInfoJson[0].price}€</p>
            </div>
            `;
    
            itemsTicketFlex.appendChild(card);
        }
        openItemsTicket();
    // } catch (error) {
    //     console.error('Error:', error);
    // }
}