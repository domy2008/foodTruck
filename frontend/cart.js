// Access data based on the chosen passing method:

// 1. Query String Parameters:
const queryString = new URLSearchParams(window.location.search);
const applicant = decodeURIComponent(queryString.get('applicant'));

// 2. Local Storage:
const storedItem = localStorage.getItem('selectedItem');
const item = JSON.parse(storedItem); // Assuming stored data is valid JSON

// Create a reference to the list element (#item-details-list):
const itemDetailsList = document.getElementById('item-details-list');

if (item) { // Check for either parameter (depending on chosen method)
  const data = item; // Use data from either source

  // Loop through each key-value pair in the data object:
  for (const key in data) {
    const listItem = document.createElement('li');
    listItem.textContent = `${key}: ${data[key]}`;
    itemDetailsList.appendChild(listItem);
  }

  // Clear local storage if using for temporary storage:
  localStorage.removeItem('selectedItem'); // Optional
} else {
  const listItem = document.createElement('li');
  listItem.textContent = 'No data available for this item.';
  itemDetailsList.appendChild(listItem);
}

// Event listener for order button click:
const orderButton = document.getElementById('order-button');
orderButton.addEventListener('click', handleOrderClick);

orderButton.addEventListener('click', handleOrderClick);

function handleOrderClick() {
  // Display order confirmation message using alert (can be replaced with a modal or other mechanisms)
  alert('Order is on the way!');

  // Redirect to index.html
  window.location.href = 'index.html';
}
