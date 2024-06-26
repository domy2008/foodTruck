const dataList = document.getElementById('data-list');

try {
  var datas = localStorage.getItem('searchResults');

  const temp = JSON.parse(datas);
  //const searchResults = JSON.parse(temp);
  const searchResults = temp;
 

  if (Array.isArray(searchResults)) {
      // Process the parsed JSON array
      searchResults.forEach(item => {
        const listItem = document.createElement('li');
        listItem.innerHTML = `
            <h2>Address:  ${item.address}</h2>
            <p>Applicant: ${item.applicant}</p>
        `;
    
        // Add click or touch event listener (depending on device support)
        listItem.addEventListener('click', () => {
            handleListItemClick(item); // Call function to handle click/touch
        });
    
        dataList.appendChild(listItem);
    });
    
    
  } else {
      console.log("searchResults is not an array");
  }
} catch (error) {
  console.error("Error parsing search results:", error);
}

function handleListItemClick(item) {
  // Choose the most suitable data passing method based on your project structure:

  // 1. Query String Parameters (Simple but has limitations on data length and security):
  //const applicantData = encodeURIComponent(item.applicant); // Encode for URL safety
  localStorage.setItem('selectedItem', JSON.stringify(item));
  // 2. Local Storage (Suitable for smaller amounts of data):
  
  window.open('cart.html', '_blank');

  // 3. JavaScript Frameworks (Routing mechanisms enhance data management):
  // If applicable, use your framework's routing system for efficient data handling.
}
