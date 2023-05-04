var today = new Date();

// Set the min and max dates
var minDate = formatDate(today);
var maxDate = formatDate(new Date(today.getTime() + 7 * 24 * 60 * 60 * 1000)); // 7 days later

// Set the min and max attributes of the input element
document.getElementById("myDateInput").setAttribute("min", minDate);
document.getElementById("myDateInput").setAttribute("value", minDate);
document.getElementById("myDateInput").setAttribute("max", maxDate);


// Function to format a date as "YYYY-MM-DD" for use in an input element
function formatDate(date) {
  var year = date.getFullYear();
  var month = ("0" + (date.getMonth() + 1)).slice(-2);
  var day = ("0" + date.getDate()).slice(-2);
  return year + "-" + month + "-" + day;
}


// Booking callAPI
const BookingAPI = "http://localhost:8082/bookings-user"
//Get DATA Booking

function getData(){
}


// POST API
function booking(){
  $.ajax({
    type : "POST",
    url : BookingAPI,
    contentType : 'application/json',
    data : JSON.stringify(data),
    success : function(result){
        alert(" Bạn đã dặt sân thành công");
    },
    error: function(){
      alert(" Lịch này đã được đặt trước đó ")
    }

  })
}


// Thông tin sân
function updatePage(data){
  $("#sport_field_name").val("Sân bóng" + data.name)
}