let menuItems = JSON.parse(localStorage.getItem("menuItems")) || [];

// Simulated API calls
function apiCreateItem(item) {
    return new Promise((resolve) => {
        setTimeout(() => {
            menuItems.push(item);
            localStorage.setItem("menuItems", JSON.stringify(menuItems));
            resolve(item);
        }, 300);
    });
}

function apiGetAllItems() {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(menuItems);
        }, 300);
    });
}

function apiUpdateItem(index, updatedItem) {
    return new Promise((resolve) => {
        setTimeout(() => {
            menuItems[index] = updatedItem;
            localStorage.setItem("menuItems", JSON.stringify(menuItems));
            resolve(updatedItem);
        }, 300);
    });
}

function apiDeleteItem(index) {
    return new Promise((resolve) => {
        setTimeout(() => {
            menuItems.splice(index, 1);
            localStorage.setItem("menuItems", JSON.stringify(menuItems));
            resolve();
        }, 300);
    });
}

const menuForm = document.getElementById("menuForm");
const menuTableBody = document.querySelector("#menuTable tbody");

function renderMenuItems() {
    menuTableBody.innerHTML = "";
    menuItems.forEach((item, index) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${item.name}</td>
            <td>${item.description}</td>
            <td>${item.category}</td>
            <td>₹${item.price}</td>
            <td>${item.availability}</td>
            <td>
                <button class="action-btn edit" onclick="editItem(${index})">Edit</button>
                <button class="action-btn delete" onclick="deleteItem(${index})">Delete</button>
            </td>
        `;
        menuTableBody.appendChild(row);
    });
}

menuForm.addEventListener("submit", function (e) {
    e.preventDefault();
    const newItem = {
        name: document.getElementById("itemName").value,
        description: document.getElementById("itemDesc").value,
        category: document.getElementById("itemCategory").value,
        price: parseFloat(document.getElementById("itemPrice").value).toFixed(2),
        availability: document.getElementById("itemAvailability").value
    };

    const editIndex = document.getElementById("editIndex").value;
    if (editIndex === "") {
        apiCreateItem(newItem).then(() => {
            renderMenuItems();
            menuForm.reset();
        });
    } else {
        if (confirm("Are you sure you want to update this item?")) {
            apiUpdateItem(editIndex, newItem).then(() => {
                renderMenuItems();
                menuForm.reset();
                document.getElementById("editIndex").value = "";
                menuForm.querySelector("button").textContent = "Add Item";
            });
        }
    }
});

function editItem(index) {
    const item = menuItems[index];
    document.getElementById("itemName").value = item.name;
    document.getElementById("itemDesc").value = item.description;
    document.getElementById("itemCategory").value = item.category;
    document.getElementById("itemPrice").value = item.price;
    document.getElementById("itemAvailability").value = item.availability;
    document.getElementById("editIndex").value = index;
    menuForm.querySelector("button").textContent = "Update Item";
}

function deleteItem(index) {
    if (confirm("Are you sure you want to delete this item?")) {
        apiDeleteItem(index).then(() => {
            renderMenuItems();
        });
    }
}

// Initial load
apiGetAllItems().then(() => renderMenuItems());