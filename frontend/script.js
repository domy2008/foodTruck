const searchBar = document.getElementById('search-bar');
const searchBtn = document.getElementById('search-btn');

const BASE_URL = 'http://localhost:9090'; // replace with your IP address and port

searchBtn.addEventListener('click', function() {
  const searchTerm = searchBar.value;
  // Simulate API call with mock data (replace with your backend logic)
  const results = getSearchResults(searchTerm);

  if (results.length > 0) {
    window.location.href = `results.html?term=${searchTerm}`;
  } else {
    alert('No results found for your search term.');
  }
});

function getSearchResults(searchTerm) {

  if(searchTerm === "") {
    searchTerm = "Chicken";
  }
  // Call the backend Java API using fetch
  fetch(`${BASE_URL}/api/getAllMatchedFood?subItems=${searchTerm}`, {
    method: "GET", // Assuming a GET request for your API
    headers: {
      "Content-Type": "application/json", // Adjust as needed for your API
      "Origin": "http://127.0.0.1:5500"
    }
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("API request failed");
      }
      return response.json(); // Assuming the API returns JSON data
    })
    .then((data) => {
      // Handle the received data from the API
      //print the data to the console
     // console.log("Received data from API:", data);

      const stringifiedData = JSON.stringify(data);
      console.log(stringifiedData);
      localStorage.setItem('searchResults', JSON.stringify(stringifiedData));

      console.log("*************************************************");
      window.location.href = `results.html`;
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
      // Handle errors gracefully, e.g., display an error message to the user
    });
}



