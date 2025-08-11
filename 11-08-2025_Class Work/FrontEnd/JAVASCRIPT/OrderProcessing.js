// 1. Store menu items with prices
const menu = [
    { name: "Pizza", price: 200 },
    { name: "Burger", price: 120 },
    { name: "Pasta", price: 150 },
    { name: "Fries", price: 80 }
];

// Display menu
let menuHtml = "<h3>Menu</h3><ul>";
for (let item of menu) {
    menuHtml += `<li>${item.name} - ₹${item.price}</li>`;
}
menuHtml += "</ul>";
document.getElementById("menu").innerHTML = menuHtml;

// 2. Take customer's order
let orders = [];
let ordering = true;
while (ordering) {
    let order = prompt("Enter item name to order (or type 'done' to finish):");
    if (order && order.toLowerCase() !== "done") {
        // Find item in menu
        let found = false;
        for (let item of menu) {
            if (item.name.toLowerCase() === order.toLowerCase()) {
                orders.push(item);
                found = true;
                break;
            }
        }
        if (!found) {
            alert("Item not found in menu!");
        }
    } else {
        ordering = false;
    }
}

// 3. Calculate total price with discount
let total = 0;
for (let item of orders) {
    total += item.price;
}
let discount = 0;
if (total >= 500) {
    discount = total * 0.1; // 10% discount
}
let finalTotal = total - discount;

// 4. Loop through orders to display them
let ordersHtml = "<h3>Your Orders</h3><ul>";
for (let item of orders) {
    ordersHtml += `<li>${item.name} - ₹${item.price}</li>`;
}
ordersHtml += "</ul>";
ordersHtml += `<p>Total: ₹${total}</p>`;
if (discount > 0) {
    ordersHtml += `<p>Discount: ₹${discount}</p>`;
}
ordersHtml += `<p><strong>Final Total: ₹${finalTotal}</strong></p>`;

document.getElementById("orders").innerHTML = ordersHtml;