// $(document).ready(function() {
//     // 获取userId，假设它通过某种方式传递到前端
//     var userId = $('#userId').data('userId');
//
//     // 发送AJAX请求获取现有地址
//     function fetchExistingAddresses(userId) {
//         $.ajax({
//             url: 'getaddress',
//             type: 'GET',
//             data: { userId: userId },
//             success: function(data) {
//                 if (data.status === 'success') {
//                     if (data.addresses.length > 0) {
//                         // 如果有现有地址，填充到下拉框中
//                         console.log('获取现有地址:', data.addresses);
//                         populateExistingAddresses(data.addresses);
//                     } else {
//                         // 如果没有现有地址，禁用“使用现有地址”选项，并显示新地址输入框
//                         $('#useExistingAddress').prop('disabled', true);
//                         $('#existingAddressContainer').hide();
//                         $('#newAddressContainer').show();
//                         $('#useNewAddress').prop('checked', true);
//                     }
//                 } else {
//                     console.error('获取现有地址失败:', data.message);
//                 }
//             },
//             error: function(xhr, status, error) {
//                 console.error('请求失败:', error);
//             }
//         });
//     }
//
//     // 填充现有地址到下拉框
//     function populateExistingAddresses(addresses) {
//         var existingAddressSelect = $('#existingAddress');
//         existingAddressSelect.empty(); // 清空现有选项
//         addresses.forEach(function(address) {
//             existingAddressSelect.append($('<option>').val(address.id).text(address.address));
//         });
//         console.error('地址:');
//     }
//
//     // 监听地址选项的变更事件
//     $('input[name="addressOption"]').change(function() {
//         if (this.value === 'existing') {
//             $('#existingAddressContainer').show();
//             $('#newAddressContainer').hide();
//         } else if (this.value === 'new') {
//             $('#existingAddressContainer').hide();
//             $('#newAddressContainer').show();
//         }
//     });
//
//     // 页面加载时调用
//     fetchExistingAddresses(userId);
// });
$(document).ready(function() {
    var userId = $('#userId').data('user-id'); // 获取用户 ID

    // 获取现有地址的函数
    function fetchExistingAddresses(userId) {
        $.ajax({
            url: 'getaddress', // 你的后端地址
            type: 'GET',
            data: { userId: userId },
            success: function(data) {
                if (data.status === 'success') {
                    populateExistingAddresses(data.addresses);
                } else {
                    console.error('获取现有地址失败:', data.message);
                }
            },
            error: function(xhr, status, error) {
                console.error('请求失败:', error);
            }
        });
    }

    // 填充现有地址到下拉框
    function populateExistingAddresses(addresses) {
        console.error('dz:', addresses);
        var existingAddressSelect = $('#existingAddress');
        existingAddressSelect.empty(); // 清空现有选项
        addresses.forEach(function(address) {
            console.error('dz:', address);
            console.error('dz:', address.id);
            console.error('dz:', address.address);
            existingAddressSelect.append($('<option>').val(address).text(address));
        });
    }

    // 处理地址选择
    $('input[name="addressOption"]').change(function() {
        if (this.value === 'existing') {
            $('#existingAddressContainer').show();
            $('#newAddressContainer').hide();
        } else if (this.value === 'new') {
            $('#existingAddressContainer').hide();
            $('#newAddressContainer').show();
        }
    });

    // 在页面加载时获取现有地址
    fetchExistingAddresses(userId);
});
