$(document).ready(function() {
    // 获取用户ID（假设通过data属性存储）
    const userId = $('#userId').data('user-id');

    // 初始化地址显示状态
    function initAddressVisibility() {
        const isExisting = $('input[name="addressType"]:checked').val() === 'existing';
        $('#existingAddressContainer').toggle(isExisting); // true显示/false隐藏
        $('#newAddressContainer').toggle(!isExisting);
    }

    // 地址类型切换事件
    $('input[name="addressType"]').change(function() {
        initAddressVisibility(); // 实时更新显示状态
    });

    // 获取并填充现有地址
    function fetchExistingAddresses(userId) {
        $.ajax({
            url: '/order/getaddress',
            type: 'GET',
            data: { userId: userId },
            success: function(data) {
                if (data?.status === 'success' && Array.isArray(data.addresses)) {
                    if (data.addresses.length > 0) {
                        populateExistingAddresses(data.addresses);
                        $('input[name="addressType"][value="existing"]').prop('disabled', false); // 启用现有地址选项
                    } else {
                        // 没有地址时强制切换到新地址
                        $('input[name="addressType"][value="new"]').prop('checked', true);
                        $('input[name="addressType"][value="existing"]').prop('disabled', true); // 禁用现有地址选项
                        initAddressVisibility(); // 更新显示状态
                    }
                }
            },
            error: function(xhr, status, error) {
                console.error('请求失败:', error);
            }
        });
    }

    // 填充地址到下拉框
    function populateExistingAddresses(addresses) {
        const $select = $('#existingAddress').empty();

        addresses.forEach(address => {
            $select.append(
                $('<option>', {
                    value: address,  // 如果后续需要ID可以改为 address.id
                    text: address    // 如果返回对象则改为 address.text
                })
            );
        });
    }

    // 页面初始化
    initAddressVisibility();       // 初始化显示状态
    fetchExistingAddresses(userId); // 加载地址数据
});