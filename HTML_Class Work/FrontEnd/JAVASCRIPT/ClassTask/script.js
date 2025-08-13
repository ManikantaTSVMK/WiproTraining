let editIndex = null;

function showSection(id) {
    document.querySelectorAll(".section").forEach(sec => sec.classList.remove("active"));
    document.getElementById(id).classList.add("active");

    if (id === "view") renderItems();
}

document.getElementById("menuForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const newItem = {
        name: document.getElementById("itemName").value,
        description: document.getElementById("itemDescription").value,
        category: document.getElementById("itemCategory").value,
        price: document.getElementById("itemPrice").value,
        availability: document.getElementById("itemAvailability").value
    };

    let items = JSON.parse(localStorage.getItem("menuItems")) || [];

    if (editIndex !== null) {
        items[editIndex] = newItem;
        editIndex = null;
    } else {
        items.push(newItem);
    }

    localStorage.setItem("menuItems", JSON.stringify(items));
    document.getElementById("menuForm").reset();
    showSection("view");
});

function renderItems() {
    const items = JSON.parse(localStorage.getItem("menuItems")) || [];
    const tableBody = document.getElementById("menuTableBody");
    tableBody.innerHTML = "";

    items.forEach((item, index) => {
        tableBody.innerHTML += `
            <tr>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>${item.category}</td>
                <td>₹${item.price}</td>
                <td>${item.availability}</td>
                <td>
                    <button onclick="editItem(${index})">Edit</button>
                    <button onclick="deleteItem(${index})">Delete</button>
                </td>
            </tr>
        `;
    });
}

function editItem(index) {
    const items = JSON.parse(localStorage.getItem("menuItems")) || [];
    const item = items[index];

    document.getElementById("itemName").value = item.name;
    document.getElementById("itemDescription").value = item.description;
    document.getElementById("itemCategory").value = item.category;
    document.getElementById("itemPrice").value = item.price;
    document.getElementById("itemAvailability").value = item.availability;

    editIndex = index;
    showSection("add");
}

function deleteItem(index) {
    let items = JSON.parse(localStorage.getItem("menuItems")) || [];
    items.splice(index, 1);
    localStorage.setItem("menuItems", JSON.stringify(items));
    renderItems();
}

function searchItems() {
    const query = document.getElementById("searchBox").value.toLowerCase();
    const items = JSON.parse(localStorage.getItem("menuItems")) || [];
    const filtered = items.filter(item =>
        item.name.toLowerCase().includes(query) ||
        item.description.toLowerCase().includes(query) ||
        item.category.toLowerCase().includes(query)
    );
    const tableBody = document.getElementById("menuTableBody");
    tableBody.innerHTML = "";
    filtered.forEach((item, index) => {
        tableBody.innerHTML += `
            <tr>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>${item.category}</td>
                <td>₹${item.price}</td>
                <td>${item.availability}</td>
                <td>
                    <button onclick="editItem(${index})">Edit</button>
                    <button onclick="deleteItem(${index})">Delete</button>
                </td>
            </tr>
        `;
    });
}
