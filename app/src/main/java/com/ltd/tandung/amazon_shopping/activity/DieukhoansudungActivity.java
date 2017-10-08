package com.ltd.tandung.amazon_shopping.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ltd.tandung.amazon_shopping.R;

public class DieukhoansudungActivity extends AppCompatActivity {
    TextView txtDieukhoan;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dieukhoansudung);
        toolbar = (Toolbar) findViewById(R.id.toolbarID);
        Actiontoolbar();
        txtDieukhoan = (TextView) findViewById(R.id.txtDieuKhoan);
        txtDieukhoan.setText("Điều Khoản Sử Dụng\n" +
                "1. Giới thiệu\n" +
                "\n" +
                "Chào mừng quý khách hàng đến với Amazon.vn.\n" +
                "\n" +
                "Chúng tôi là Công ty Cổ phần Amazon có địa chỉ trụ sở tại Hà Nội, địa chỉ giao dịch: Chợ Trời-Hà Nội, thành lập Sàn giao dịch thương mại điện tử thông qua website www.amazon.vn và đã được đăng ký chính thức với Bộ Công Thương Việt Nam.\n" +
                "\n" +
                "Khi quý khách hàng truy cập vào trang website của chúng tôi có nghĩa là quý khách đồng ý với các điều khoản này. Trang web có quyền thay đổi, chỉnh sửa, thêm hoặc lược bỏ bất kỳ phần nào trong Điều khoản mua bán hàng hóa này, vào bất cứ lúc nào. Các thay đổi có hiệu lực ngay khi được đăng trên trang web mà không cần thông báo trước. Và khi quý khách tiếp tục sử dụng trang web, sau khi các thay đổi về Điều khoản này được đăng tải, có nghĩa là quý khách chấp nhận với những thay đổi đó.\n" +
                "\n" +
                "Quý khách hàng vui lòng kiểm tra thường xuyên để cập nhật những thay đổi của chúng tôi.\n" +
                "\n" +
                "2. Hướng dẫn sử dụng website\n" +
                "\n" +
                "Khi vào web của chúng tôi, khách hàng phải đảm bảo đủ 18 tuổi, hoặc truy cập dưới sự giám sát của cha mẹ hay người giám hộ hợp pháp. Khách hàng đảm bảo có đầy đủ hành vi dân sự để thực hiện các giao dịch mua bán hàng hóa theo quy định hiện hành của pháp luật Việt Nam.\n" +
                "\n" +
                "Chúng tôi sẽ cấp một tài khoản (Account) sử dụng để khách hàng có thể mua sắm trên website Amazon.vn trong khuôn khổ Điều khoản và Điều kiện sử dụng đã đề ra.\n" +
                "\n" +
                "Quý khách hàng sẽ phải đăng ký tài khoản với thông tin xác thực về bản thân và phải cập nhật nếu có bất kỳ thay đổi nào. Mỗi người truy cập phải có trách nhiệm với mật khẩu, tài khoản và hoạt động của mình trên web. Hơn nữa, quý khách hàng phải thông báo cho chúng tôi biết khi tài khoản bị truy cập trái phép. Chúng tôi không chịu bất kỳ trách nhiệm nào, dù trực tiếp hay gián tiếp, đối với những thiệt hại hoặc mất mát gây ra do quý khách không tuân thủ quy định.\n" +
                "\n" +
                "Nghiêm cấm sử dụng bất kỳ phần nào của trang web này với mục đích thương mại hoặc nhân danh bất kỳ đối tác thứ ba nào nếu không được chúng tôi cho phép bằng văn bản. Nếu vi phạm bất cứ điều nào trong đây, chúng tôi sẽ hủy tài khoản của khách mà không cần báo trước.\n" +
                "\n" +
                "Trong suốt quá trình đăng ký, quý khách đồng ý nhận email quảng cáo từ website. Nếu không muốn tiếp tục nhận mail, quý khách có thể từ chối bằng cách nhấp vào đường link ở dưới cùng trong mọi email quảng cáo.\n" +
                "\n" +
                "3. Ý kiến của khách hàng\n" +
                "\n" +
                "Tất cả nội dung trang web và ý kiến phê bình của quý khách đều là tài sản của chúng tôi. Nếu chúng tôi phát hiện bất kỳ thông tin giả mạo nào, chúng tôi sẽ khóa tài khoản của quý khách ngay lập tức hoặc áp dụng các biện pháp khác theo quy định của pháp luật Việt Nam.\n" +
                "\n" +
                "4. Chấp nhận đơn hàng và giá cả\n" +
                "\n" +
                "Chúng tôi có quyền từ chối hoặc hủy đơn hàng của quý khách vì bất kỳ lý do gì liên quan đến lỗi kỹ thuật, hệ thống một cách khách quan vào bất kỳ lúc nào.\n" +
                "\n" +
                "Ngoài ra, để đảm bảo tính công bằng cho khách hàng là người tiêu dùng cuối cùng của Amazon.vn, chúng tôi cũng sẽ từ chối các đơn hàng không nhằm mục đích sử dụng cho cá nhân, mua hàng số lượng nhiều hoặc với mục đích mua đi bán lại.\n" +
                "\n" +
                "Chúng tôi cam kết sẽ cung cấp thông tin giá cả chính xác nhất cho người tiêu dùng. Tuy nhiên, đôi lúc vẫn có sai sót xảy ra, ví dụ như trường hợp giá sản phẩm không hiển thị chính xác trên trang web hoặc sai giá, tùy theo từng trường hợp chúng tôi sẽ liên hệ hướng dẫn hoặc thông báo hủy đơn hàng đó cho quý khách. Chúng tôi cũng có quyền từ chối hoặc hủy bỏ bất kỳ đơn hàng nào dù đơn hàng đó đã hay chưa được xác nhận hoặc đã thanh toán.\n" +
                "\n" +
                "5. Thay đổi hoặc hủy bỏ giao dịch tại Amazon.vn\n" +
                "\n" +
                "Trong mọi trường hợp, khách hàng đều có quyền chấm dứt giao dịch nếu đã thực hiện các biện pháp sau đây:\n" +
                "\n" +
                "Thông báo cho Amazon.vn về việc hủy giao dịch qua đường dây nóng (hotline) 0982753447 hoặc lời ghi nhắn tại http://hotro.Amazon.vn/hc/vi/requests/new\n" +
                "Trả lại hàng hoá đã nhận nhưng chưa sử dụng hoặc hưởng bất kỳ lợi ích nào từ hàng hóa đó (theo quy định của chính sách đổi trả hàng tại http://www.amazon.vn/chinh-sach-doi-tra-hang)\n" +
                "6. Giải quyết hậu quả do lỗi nhập sai thông tin tại Amazon.vn\n" +
                "\n" +
                "Khách hàng có trách nhiệm cung cấp thông tin đầy đủ và chính xác khi tham gia giao dịch tại Amazon.vn. Trong trường hợp khách hàng nhập sai thông tin và gửi vào trang TMĐT Amazon.vn, Amazon.vn có quyền từ chối thực hiện giao dịch. Ngoài ra, trong mọi trường hợp, khách hàng đều có quyền đơn phương chấm dứt giao dịch nếu đã thực hiện các biện pháp sau đây:\n" +
                "\n" +
                "Thông báo cho Amazon.vn qua đường dây nóng 0982753447 hoặc lời nhập nhắn tại địa chỉ http://hotro.amazon.vn/hc/vi/requests/new\n" +
                "Trả lại hàng hoá đã nhận nhưng chưa sử dụng hoặc hưởng bất kỳ lợi ích nào từ hàng hóa đó.\n" +
                "Trong trường hợp sai thông tin phát sinh từ phía Amazon.vn mà Amazon.vn có thể chứng minh đó là lỗi của hệ thống hoặc từ bên thứ ba (sai giá sản phẩm, sai xuất xứ, …), Amazon.vn sẽ đền bù cho khách hàng một mã giảm giá cho các lần mua sắm tiếp theo với mệnh giá tùy từng trường hợp cụ thể và có quyền không thực hiện giao dịch bị lỗi.\n" +
                "\n" +
                "7. Thương hiệu và bản quyền\n" +
                "\n" +
                "Mọi quyền sở hữu trí tuệ (đã đăng ký hoặc chưa đăng ký), nội dung thông tin và tất cả các thiết kế, văn bản, đồ họa, phần mềm, hình ảnh, video, âm nhạc, âm thanh, biên dịch phần mềm, mã nguồn và phần mềm cơ bản đều là tài sản của chúng tôi. Toàn bộ nội dung của trang web được bảo vệ bởi luật bản quyền của Việt Nam và các công ước quốc tế. Bản quyền đã được bảo lưu.\n" +
                "\n" +
                "8. Quyền pháp lý\n" +
                "\n" +
                "Các điều kiện, điều khoản và nội dung của trang web này được điều chỉnh bởi luật pháp Việt Nam và Tòa án có thẩm quyền tại Việt Nam sẽ giải quyết bất kỳ tranh chấp nào phát sinh từ việc sử dụng trái phép trang web này.\n" +
                "\n" +
                "9. Quy định về bảo mật\n" +
                "\n" +
                "Trang web của chúng tôi coi trọng việc bảo mật thông tin và sử dụng các biện pháp tốt nhất bảo vệ thông tin và việc thanh toán của quý khách. Thông tin của quý khách trong quá trình thanh toán sẽ được mã hóa để đảm bảo an toàn. Sau khi quý khách hoàn thành quá trình đặt hàng, quý khách sẽ thoát khỏi chế độ an toàn.\n" +
                "\n" +
                "Quý khách không được sử dụng bất kỳ chương trình, công cụ hay hình thức nào khác để can thiệp vào hệ thống hay làm thay đổi cấu trúc dữ liệu. Trang web cũng nghiêm cấm việc phát tán, truyền bá hay cổ vũ cho bất kỳ hoạt động nào nhằm can thiệp, phá hoại hay xâm nhập vào dữ liệu của hệ thống. Cá nhân hay tổ chức vi phạm sẽ bị tước bỏ mọi quyền lợi cũng như sẽ bị truy tố trước pháp luật nếu cần thiết.\n" +
                "\n" +
                "Mọi thông tin giao dịch sẽ được bảo mật ngoại trừ trong trường hợp cơ quan pháp luật yêu cầu.\n" +
                "\n" +
                "10. Thanh toán an toàn và tiện lợi tại Amazon.vn\n" +
                "\n" +
                "Người mua có thể tham khảo các phương thức thanh toán sau đây và lựa chọn áp dụng phương thức phù hợp:\n" +
                "\n" +
                "Cách 1: Thanh toán trực tiếp (người mua nhận hàng tại địa chỉ người bán):\n" +
                "\n" +
                "Bước 1: Người mua tìm hiểu thông tin về sản phẩm, dịch vụ được đăng tin;\n" +
                "\n" +
                "Bước 2: Người mua đến địa chỉ bán hàng\n" +
                "\n" +
                "Bước 3: Người mua thanh toán và nhận hàng;\n" +
                "\n" +
                "Cách 2: Thanh toán sau (COD – giao hàng và thu tiền tận nơi):\n" +
                "\n" +
                "Bước 1: Người mua tìm hiểu thông tin về sản phẩm, dịch vụ được đăng tin;\n" +
                "\n" +
                "Bước 2: Người mua xác thực đơn hàng (điện thoại, tin nhắn, email);\n" +
                "\n" +
                "Bước 3: Người bán xác nhận thông tin Người mua;\n" +
                "\n" +
                "Bước 4: Người bán chuyển hàng;\n" +
                "\n" +
                "Bước 5: Người mua nhận hàng và thanh toán.\n" +
                "\n" +
                "Cách 3: Thanh toán online qua thẻ tín dụng, chuyển khoản\n" +
                "\n" +
                "Bước 1: Người mua tìm hiểu thông tin về sản phẩm, dịch vụ được đăng tin;\n" +
                "\n" +
                "Bước 2: Người mua xác thực đơn hàng (điện thoại, tin nhắn, email);\n" +
                "\n" +
                "Bước 3: Người bán xác nhận thông tin Người mua;\n" +
                "\n" +
                "Bước 4: Ngưởi mua thanh toán;\n" +
                "\n" +
                "Bước 5: Người bán chuyển hàng;\n" +
                "\n" +
                "Bước 6: Người mua nhận hàng.\n" +
                "\n" +
                "Đối với người mua hàng từ Amazon.vn thì phải tuẩn thu theo chính sách thanh toán của công ty.\n" +
                "\n" +
                "11. Đảm bảo an toàn giao dịch tại Amazon.vn\n" +
                "\n" +
                "Chúng tôi sử dụng các dịch vụ để bảo vệ thông tin về nội dung mà người bán đăng  sản phẩm trên Amazon.vn. Để đảm bảo các giao dịch được tiến hành thành công, hạn chế tối đa rủi ro có thể phát sinh.\n" +
                "\n" +
                "12. Giải quyết tranh chấp\n" +
                "\n" +
                "Bất kỳ tranh cãi, khiếu nại hoặc tranh chấp phát sinh từ hoặc liên quan đến giao dịch tại Amazon.vn hoặc các Quy định và Điều kiện này đều sẽ được giải quyết bằng hình thức thương lượng, hòa giải, trọng tài và/hoặc Tòa án theo Luật bảo vệ Người tiêu dùng Chương 4 về Giải quyết tranh chấp giữa người tiêu dùng và tổ chức, cá nhân kinh doanh hàng hóa, dịch vụ.\n" +
                "\n" +
                "13. Luật pháp và thẩm quyền tại Lãnh thổ Việt Nam\n" +
                "\n" +
                "Tất cả các Điều Khoản và Điều Kiện này và Hợp Đồng (và tất cả nghĩa vụ phát sinh ngoài hợp đồng hoặc có liên quan) sẽ bị chi phối và được hiểu theo luật pháp của Việt Nam. Nếu có tranh chấp phát sinh bởi các Quy định Sử dụng này, quý khách hàng có quyền gửi khiếu nại/khiếu kiện lên Tòa án có thẩm quyền tại Việt Nam để giải quyết.");
    }

    private void Actiontoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
