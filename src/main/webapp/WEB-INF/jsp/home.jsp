<html>
  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>Original Case Web Application</title>
  </head>
  <body>
    <br></br>
    <div id="fareSearch">
      
       <form id="FARE_FORM" action="http://localhost:9000/fare" method="post">
        <table>
          <tr> 
            <td> <input id="originId" name="origin" onkeyup="originFunction()" onselect="return false;" list="originList" />
                 <datalist id="originList"></datalist>
            </td>
             <td> 
                <input id="destinationId" name="destination"  onkeyup="destinationFunction()" list="destinationList" />
                <datalist id="destinationList"></datalist>
              </td>
            <td>
             <input id="currencyId" name="currency"  list="currencyList"  /> </td>
             <datalist id="currencyList"></datalist>
           </tr>
         </table>
         
         <p>
           <input type="submit" name="Submit" value="search" />
         </p>
       </form>

    </div>
  </body>
</html>
<script>  
 
 
 function originFunction() {
  var input = document.getElementById('originId').value;
  var currencyId = document.getElementById('currencyId').value; 
  $('originList').empty();
  var originList = $('originList');
  var myOrigins;  
  $.ajax({
	  type:'POST',
	  url : "http://localhost:9000/findLocation?term="+input+"&currency="+currencyId ,
	  contentType: 'application/json',
	  success: function(response){
		  console.log(response);
		  addOrigins(response);
	  }
  });
}

 function addOrigins(response){
	  myOrigins = response;
	  const addToList = function(item) {
		  var option = document.createElement('option');
		  option.value = item;
		  originList.appendChild(option);
		};
		myOrigins.forEach( x => addToList(x) );
 }
 
 
 function destinationFunction() {
	  var input = document.getElementById('destinationId').value;
	  var currencyId = document.getElementById('currencyId').value;	  
	  $('destinationList').empty();
	  var destinationList = $('destinationList');
	  var myDestinations;  
	  $.ajax({
		  type:'POST',
		  url : "http://localhost:9000/findLocation?term="+input+"&currency="+currencyId ,
		  contentType: 'application/json',
		  success: function(response){
			  console.log(response);
			  addDestinations(response);
		  }
	  });
	}
	 function addDestinations(response){
		 myDestinations = response;
		  const addToList2 = function(item) {
			  var option = document.createElement('option');
			  option.value = item;
			  destinationList.appendChild(option);
			};
			myDestinations.forEach( x => addToList2(x) );
	 }
	 
 var mycurrency = ['EUR','USD'];
var currencyList = document.getElementById('currencyList');

  mycurrency.forEach(function(item){
  var option = document.createElement('option');
  option.value = item;
  currencyList.appendChild(option);
});
</script>