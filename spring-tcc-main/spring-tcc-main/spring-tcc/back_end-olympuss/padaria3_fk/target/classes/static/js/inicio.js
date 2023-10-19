document.querySelector(".jsFilter").addEventListener("click", function () {
    document.querySelector(".filter-menu").classList.toggle("active");
  });
  
  const searchWrapper = document.querySelector(".search");
  const inputBox = searchWrapper.querySelector("input");
  const sugestBox = searchWrapper.querySelector(".list");
  const icon = searchWrapper.querySelector(".icon");
  let linkTag = searchWrapper.querySelector("a");
  let webLink;
  
  inputBox.onkeyup = (e)=>{
      let userData = e.target.value; //user enetered data
      let emptyArray = [];
  
      if (e.key === 'Enter'){
        if(userData){
          window.open(`https://www.google.com/search?q=${userData}`, '_blank')
        }
      }
  
      if(userData){
          icon.onclick = ()=>{
              webLink = `https://www.google.com/search?q=${userData}`;
              linkTag.setAttribute("href", webLink);
              linkTag.click();
          }
          emptyArray = suggestions.filter((data)=>{
              //filtering array value and user characters to lowercase and return only those words which are start with user enetered chars
              return data.toLocaleLowerCase().startsWith(userData.toLocaleLowerCase());
          });
          emptyArray = emptyArray.map((data)=>{
              // passing return data inside li tag
              return data = `<li>${data}</li>`;
          });
          searchWrapper.classList.add("active"); //show autocomplete box
          showSuggestions(emptyArray);
          let allList = sugestBox.querySelectorAll("li");
          for (let i = 0; i < allList.length; i++) {
              //adding onclick attribute in all li tag
              allList[i].setAttribute("onclick", "select(this)");
          }
  
          if (e.key === 'Escape'){
            searchWrapper.classList.remove("active");
          }
      }else{
          searchWrapper.classList.remove("active"); //hide autocomplete box
      }
  }
  
  function select(element){
      let selectData = element.textContent;
      inputBox.value = selectData;
      icon.onclick = ()=>{
          webLink = `https://www.google.com/search?q=${selectData}`;
          linkTag.setAttribute("href", webLink);
          linkTag.click();
      }
      searchWrapper.classList.remove("active");
  }
  
  function showSuggestions(list){
      let listData;
      console.log(!list.length);
      if(!list.length){
          userValue = inputBox.value;
          listData = `<li>${userValue}</li>`;
      }else{
        listData = list.join('');
      }
      sugestBox.innerHTML = listData;
  }
  document.querySelector(".grid").addEventListener("click", function () {
    document.querySelector(".list").classList.remove("active");
    document.querySelector(".grid").classList.add("active");
    document.querySelector(".products-area-wrapper").classList.add("gridView");
    document
      .querySelector(".products-area-wrapper")
      .classList.remove("tableView");
  });
  
  document.querySelector(".list").addEventListener("click", function () {
    document.querySelector(".list").classList.add("active");
    document.querySelector(".grid").classList.remove("active");
    document.querySelector(".products-area-wrapper").classList.remove("gridView");
    document.querySelector(".products-area-wrapper").classList.add("tableView");
  });
  
  var modeSwitch = document.querySelector('.mode-switch');
  modeSwitch.addEventListener('click', function () {                      document.documentElement.classList.toggle('light');
   modeSwitch.classList.toggle('active');
  });
  
  