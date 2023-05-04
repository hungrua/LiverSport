// $(function(){
//   sportFieldFindAPI="http://localhost:8082/field-user";
//   // Set the value for Seclector
//   function getValueForSecondInput(){
//     var options = {
//       'Ha Noi': {
//       '--Quận huyện--' : null, 
//       'Ba Đình': "BaDinh",
//       'Bắc Từ Liêm': "BacTuLiem",
//       'Cầu Giấy': "Caugiay",
//       'Đống Đa': "DongDa",
//       'Hà Đông': "HaDong",
//       'Hai Bà Trưng': "HaiBaTrung",
//       'Hoàn Kiếm':'HoanKiem',
//       'Hoàng Mai': 'HoangMai',
//       'Long Biên': 'LongBien',
//       'Nam Từ Liêm' :'NamTuLiem',
//       'Tây Hồ' : 'TayHo',
//       'Thanh Xuân': 'ThanhXuan'
//     },
//       'Thanh pho Ho Chi Minh': {
//         '--Quận huyện--' : null,
//         "Bình Chánh": 'BinhChanh',
//         "Bình Tân" : 'BinhTan',
//         "Bình Thạnh" :'BinhThanh',
//         "Cần Giờ": 'CanGio',
//         "Củ Chi" : 'CuChi',
//         "Quận 1" : 'Quan1',
//         "Quận 2" : 'Quan2',
//         "Quận 3": 'Quan3',
//         "Quận 4": 'Quan4',
//         "Quận 5": 'Quan5',
//         "Quận 6": 'Quan6',
//         "Quận 7": 'Quan7',
//         "Quận 8": 'Quan8',
//         "Quận 9": 'Quan9',
//         "Quận 10": 'Quan10',
//         "Quận 11": 'Quan11',
//         "Quận 12": 'Quan12',
//         "Gò Vấp": 'GoVap',
//         "Hóc Môn":'HocMon',
//         "Nhà Bè" :'NhaBe',
//         "Phú Nhuận" : 'PhuNhuan',
//         "Tân Bình" :'TanBinh',
//         "Tân Phú" :'TanPhu',
//         "Thủ Đức" : 'ThuDuc'
//       }
      
//     };
//     var select1Value = $('#cities').val();
//     var select2Options = options[select1Value];
//     console.log(select2Options)
//     var select2 = $('#district');
//     select2.empty();
//     for (let [key, value] of Object.entries(select2Options)) {
//       select2.append(`<option value="${value}">${key}</option>`);
//     }
//   }
//   //Get the search conditions by onclick
//   function searchByConditions(){
//     $('#search-btn').on('click',function(){
//       let GetObject = {
//             'city' : $('#city').val(),
//             'district': $('#district').val(),
//             'categoryId':  parseInt($('#category').val()),
//             'name': $('#name').val()
//           }
//       renderSportFieldByCondition(GetObject)
//     })
//   }
//   // Get method
//   function renderSportFieldByCondition(data){
//     $.ajax({
//       type :'GET',
//       url:  sportFieldFindAPI,
//       contentType : 'application/json',
//       data : JSON.stringify(data),
//       succes: function(reslut){
//         $.each(data,function(index,value){
//           console.log(index)
//           $("#sportFieldContainer").append(
//               `<div class="col-lg-4 col-md-6 mb-4 sport_field">
//               <div class="card h-100 h-100p">
//                 <img class="card-img-top" src="/image/sanbong/san1.jpg" alt="">
//                 <div class="card-body card-bodyp">
//                   <h4 class="card-title card-title-p">
//                     <a href="/HTML/sport_field.html" title="Sân bóng ${value.name} "> Sân bóng ${value.name}</a>
//                   </h4>
//                   <div class="addres"><span class="info-title">Địa chỉ :</span><span class="sf-info">${value.address}</span></div>
//                   <div class="price"></i><span class="info-title">Giá tham khảo :</span> <span class="sf-info price-info"> ${value.price} vnđ/ trận </span></div>
//                   <p class="card-text"><span class="info-title">Điểm nhấn :</span> <span class="sf-info">${value.information}</span></p>
//                 </div>
//                 <div class="info-btn">
//                   <button id="watch-info-btn" title="Xem chi tiết sân bóng ${value.name}">Xem chi tiết | Đặt sân </button>
//                 </div>
//               </div>
//             </div>`
//           );
//       });
//       },
//       error: function(){
//         alert('Không tìm thấy kết quả')
//       }
//     })
//   }
// })