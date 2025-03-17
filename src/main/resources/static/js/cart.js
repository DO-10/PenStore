$(document).ready(function() {
    var isUpdating = false;

    // 增加数量
    $(".increase-btn").on('click', function () {
        var goodsId = $(this).data('product-id'); // 注意：这里仍然是 data-product-id
        updateCart(goodsId, 'increase');
    });

    // 减少数量
    $(".decrease-btn").on('click', function () {
        var goodsId = $(this).data('product-id');
        var quantity = parseInt($(this).closest('tr').find('.quantity').text());
        if (quantity > 1) {
            updateCart(goodsId, 'decrease');
        }
    });

    // 删除商品
    $(".delete-btn").on('click', function () {
        var goodsId = $(this).data('product-id');
        if (confirm('确定要删除该商品吗？')) {
            updateCart(goodsId, 'delete');
        }
    });


    // 全选
    $('#selectAll').on('change', function () {
        var isChecked = $(this).prop('checked');
        console.log(isChecked);
        // 遍历所有商品复选框，更新它们的状态并触发每个复选框的 change 事件
        $('.product-checkbox').not('#selectAll').each(function () {
            $(this).prop('checked', isChecked).trigger('change');
        });
    });


    $(".product-checkbox").not('#selectAll').on('change', function () {
        var totalCheckboxes = $('.product-checkbox').not('#selectAll').length;
        var selectedCheckboxes = $('.product-checkbox:checked').not('#selectAll').length;

        // 如果所有商品都选中，全选框也要选中
        $('#selectAll').prop('checked', totalCheckboxes === selectedCheckboxes);
        var productId = $(this).data('product-id');
        var operation = $(this).prop('checked') ? 'choose' : 'unchoose';
        $('#selectAll').on('change', function () {
            console.log(`复选框状态: ${$(this).is(':checked')}`);
        });
// 选择时操作为 "choose"，取消选择时为 "unchoose"
        updateCart(productId, operation);
    });


    // 单个商品选择
    $(".product-checkbox").not('#selectAll').on('change', function () {
        var totalCheckboxes = $('.product-checkbox').not('#selectAll').length;
        var selectedCheckboxes = $('.product-checkbox:checked').not('#selectAll').length;
        $('#selectAll').prop('checked', totalCheckboxes === selectedCheckboxes);

        var goodsId = $(this).data('product-id');
        var operation = $(this).prop('checked') ? 'choose' : 'unchoose';
        updateCart(goodsId, operation);
    });


    // 更新购物车（增加、减少、删除）
    function updateCart(productId, operation) {
        var userId = $('input[name="userId"]').val();
        $.ajax({
            url: '/cart/update',
            type: 'POST',

            data: {
                userId: userId,
                goodsId: productId,
                operation: operation
            },
            success: function (response) {
                // 更新购物车表格内容
                console.log(response);
                if (operation === 'delete') {
                    $('#product_' + productId).remove(); // 删除商品行
                } else if (operation === 'chosen' || operation === "unchosen") {
                    // 更新全选按钮
                    $('#selectAll').prop('checked', response.allChosen);
                    $('.product-checkbox').on('change', function () {
                        console.log(`复选框状态: ${$(this).is(':checked')}`)
                    });
                    // 更新总价格
                    $('#totalPrice').text(response.totalPrice);
                } else {
                    // 如果是增加或减少数量，更新数量显示
                    var newQuantity = response.newQuantity;
                    $('#'+productId).text(newQuantity);
                }

                // 更新总价格
                $('#totalPrice').text(response.totalPrice);
                $('#amount').value(response.totalPrice);
            },
            error: function (xhr, status, error) {
                alert('操作失败，请稍后再试！');
            }
        });
    }})


// $(document).ready(function() {
//     var isUpdating = false;
//
//     // 增加数量
//     $(".increase-btn").on('click', function() {
//         var productId = $(this).data('product-id');
//         updateCart(productId, 'increase');
//     });
//
//     // 减少数量
//     $(".decrease-btn").on('click', function() {
//         var productId = $(this).data('product-id');
//         // 获取当前商品的数量
//         var quantity = parseInt($(this).closest('tr').find('.quantity').text());
//         // 如果数量大于1，则调用updateCart函数
//         if (quantity > 1) {
//             updateCart(productId, 'decrease');
//         }
//     });
//
//     // 删除商品
//     $(".delete-btn").on('click', function() {
//         var productId = $(this).data('product-id');
//         if (confirm('确定要删除该商品吗？')) {
//             updateCart(productId, 'delete');
//         }
//     });
//
//
//     // 全选
//     $('#selectAll').on('change', function() {
//         var isChecked = $(this).prop('checked');
//         console.log(isChecked);
//         // 遍历所有商品复选框，更新它们的状态并触发每个复选框的 change 事件
//         $('.product-checkbox').not('#selectAll').each(function() {
//             $(this).prop('checked', isChecked).trigger('change');
//         });
//     });
//
//
//     $(".product-checkbox").not('#selectAll').on('change', function() {
//         var totalCheckboxes = $('.product-checkbox').not('#selectAll').length;
//         var selectedCheckboxes = $('.product-checkbox:checked').not('#selectAll').length;
//
//         // 如果所有商品都选中，全选框也要选中
//         $('#selectAll').prop('checked', totalCheckboxes === selectedCheckboxes);
//         var productId = $(this).data('product-id');
//         var operation = $(this).prop('checked') ? 'choose' : 'unchoose';
//         $('#selectAll').on('change', function() {
//             console.log(`复选框状态: ${$(this).is(':checked')}`);
//         });
// // 选择时操作为 "choose"，取消选择时为 "unchoose"
//         updateCart(productId, operation);
//     });
//
