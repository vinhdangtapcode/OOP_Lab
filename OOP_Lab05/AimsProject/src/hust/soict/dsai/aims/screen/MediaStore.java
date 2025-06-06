package hust.soict.dsai.aims.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart; 

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Hiển thị tiêu đề
        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Hiển thị giá
        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        // Tạo panel chứa các nút
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Nút "Add to Cart"
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.addMedia(media); // Thêm media vào giỏ hàng
                JOptionPane.showMessageDialog(null, media.getTitle() + " has been added to your cart.");
            }
        });
        container.add(addToCartButton);

        // Nút "Play" (nếu media là Playable)
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Hiển thị JDialog khi phát media
                    JDialog playDialog = new JDialog();
                    playDialog.setTitle("Playing: " + media.getTitle());
                    JLabel label = new JLabel("Playing: " + media.getTitle());
                    playDialog.add(label, BorderLayout.CENTER);

                    // Cấu hình và hiển thị cửa sổ JDialog
                    playDialog.setSize(300, 150);
                    playDialog.setLocationRelativeTo(null);
                    playDialog.setModal(true);
                    playDialog.setVisible(true);

                    // Gọi phương thức play()
                    ((Playable) media).play();
                }
            });
            container.add(playButton);
        }

        // Thêm các thành phần vào panel
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        // Viền xung quanh
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
