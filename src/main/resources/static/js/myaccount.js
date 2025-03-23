$(document).ready(function(){
    // 全局状态控制
    let isRefreshing = false;
    let currentUserId = null;

    // 初始化主函数
    const init = () => {
        currentUserId = $('#userId').val();
        initTabs();
        initAddressForm();
        loadAddresses(currentUserId);
    };

    // 标签页初始化
    const initTabs = () => {
        $('.tab-list').each(function(){
            const $this = $(this);
            let $activeTab = $this.find('li.active');
            let $activePanel = $($activeTab.find('a').attr('href'));

            $this.on('click', '.tab-control', function(e) {
                e.preventDefault();
                const $clickedTab = $(this);
                const targetPanelId = this.hash;

                if (targetPanelId && !$clickedTab.is('.active')) {
                    // 切换标签状态
                    $activeTab.removeClass('active');
                    $activePanel.removeClass('active');

                    // 更新激活状态
                    $activeTab = $clickedTab.parent().addClass('active');
                    $activePanel = $(targetPanelId).addClass('active');

                    // 切换到地址页时刷新
                    if (targetPanelId === '#tab-2') {
                        loadAddresses(currentUserId);
                    }
                }
            });
        });
    };

    // 地址表单初始化
    const initAddressForm = () => {
        $('#submitBtn').off('click').on('click', function() {
            const newAddress = $('#newAddress').val().trim();

            if (!newAddress) {
                showMessage('地址不能为空', 'error');
                return;
            }

            $.post("/mypage/update", {
                userId: currentUserId,
                newAddress: newAddress
            }, function(response) {
                $('#newAddress').val('');
                loadAddresses(currentUserId);

            }).fail(function(xhr) {
                showMessage(`更新失败: ${xhr.statusText}`, 'error');
            });
        });
    };

    // 加载地址数据（带防抖）
    const loadAddresses = (userId) => {
        if (isRefreshing || !userId) return;
        isRefreshing = true;

        console.log('正在加载地址...', { userId });

        $.ajax({
            url: '/order/getaddress',
            type: 'GET',
            data: { userId },
            success: function(data) {
                console.log('地址加载响应:', data);
                if (data?.status === 'success') {
                    renderAddressList(data.addresses);
                }
            },
            error: function(xhr) {
                console.error('地址加载失败:', xhr.responseText);
                showMessage('地址加载失败', 'error');
            },
            complete: () => {
                isRefreshing = false;
                console.log('地址加载完成');
            }
        });
    };

    // 渲染地址列表（核心修复）
    const renderAddressList = (addresses) => {
        const $container = $('#addressList').empty();
        console.log('渲染地址列表:', addresses);

        if (!Array.isArray(addresses) || addresses.length === 0) {
            $container.append('<div class="no-address">暂无保存地址</div>');
            return;
        }

        // 生成地址块
        addresses.forEach(address => {
            const $block = $(`
                <div class="address-block">
                    <div class="address-content">${address}</div>
                    <div class="address-actions">
                        <button class="btn-delete" data-address="${address}">
                            删除
                        </button>
                    </div>
                </div>
            `);
            $container.append($block);
        });

        // 绑定删除事件
        bindDeleteEvents();
    };

    // 删除事件处理（事件委托）
    const bindDeleteEvents = () => {
        $('#addressList').off('click', '.btn-delete').on('click', '.btn-delete', function() {
            if (isRefreshing) return;

            const $btn = $(this);
            const address = $btn.data('address');
            console.log('触发删除:', { address });

            if (!confirm(`确定要删除地址：${address} 吗？`)) return;

            $.ajax({
                url: '/mypage/delete',
                type: 'DELETE',
                data: {
                    userId: currentUserId,
                    address: address
                },
                success: (response) => {
                    console.log('删除响应:', response);
                    if (response.status === 'success') {
                        loadAddresses(currentUserId);
                        showMessage(response.message, 'success');
                    } else {
                        showMessage(response.message || '删除失败', 'error');
                    }
                },
                error: (xhr) => {
                    console.error('删除错误:', xhr.responseText);
                    showMessage(`删除失败: ${xhr.statusText}`, 'error');
                }
            });
        });
    };

    // 显示状态消息
    const showMessage = (text, type) => {
        $('#responseMessage')
            .stop(true, true)
            .text(text)
            .removeClass('success error')
            .addClass(type)
            .fadeIn(300)
            .delay(2000)
            .fadeOut(500);
    };

    // 启动初始化
    init();
});