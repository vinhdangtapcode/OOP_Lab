package hust.soict.dsai.aims.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.Playable;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;

    public StoreScreen(Store store) {
        this.store = store;
        this.cart = new Cart(); // Khởi tạo giỏ hàng
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }

    // Phương thức tạo phần menu trên
    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    // Phương thức tạo menu bar
    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        smUpdateStore.add(new JMenuItem("Add Book"));
        smUpdateStore.add(new JMenuItem("Add CD"));
        smUpdateStore.add(new JMenuItem("Add DVD"));

        menu.add(smUpdateStore);
        menu.add(new JMenuItem("View store"));
        menu.add(new JMenuItem("View cart"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    // Phương thức tạo header
    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cart = new JButton("View cart");
        cart.setPreferredSize(new Dimension(100, 50));
        cart.setMaximumSize(new Dimension(100, 50));

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    // Cập nhật phương thức createCenter để thêm nút "Thêm vào giỏ hàng" và "Play"
    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < Math.min(9, mediaInStore.size()); i++) {
            Media media = mediaInStore.get(i);
            MediaStore cell = new MediaStore(media, cart);

            // Kiểm tra nếu MediaStore đã có các nút, không thêm nút trùng
            if (cell.getComponentCount() == 0) {
                // Tạo nút "Add to Cart"
                JButton addToCartButton = new JButton("Add to Cart");
                addToCartButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Thêm media vào giỏ hàng khi nhấn nút
                        cart.addMedia(media);
                        JOptionPane.showMessageDialog(null, media.getTitle() + " has been added to your cart.");
                    }
                });

                // Tạo nút "Play"
                JButton playButton = new JButton("Play");
                playButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Phát media trong cửa sổ JDialog khi nhấn nút "Play"
                        if (media instanceof Playable) {
                            JDialog playDialog = new JDialog();
                            playDialog.setTitle("Playing: " + media.getTitle());
                            JLabel label = new JLabel("Playing: " + media.getTitle());
                            playDialog.add(label, BorderLayout.CENTER);
                            playDialog.setSize(300, 150);
                            playDialog.setLocationRelativeTo(null);
                            playDialog.setModal(true);
                            playDialog.setVisible(true);

                            ((Playable) media).play();
                        } else {
                            JOptionPane.showMessageDialog(null, "This media cannot be played.");
                        }
                    }
                });

                // Thêm nút "Add to Cart" và "Play" vào MediaStore
                cell.add(addToCartButton);
                cell.add(playButton);
            }

            // Thêm cell vào giao diện
            center.add(cell);
        }

        return center;
    }

    public static void main(String[] args) {
        Store store = new Store(20);
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        store.addMedia(dvd1);  

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        store.addMedia(dvd2);  

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        store.addMedia(dvd3);  
        new StoreScreen(store);
    }
}
