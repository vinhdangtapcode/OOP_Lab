package hust.soict.dsai.aims;

import java.util.Scanner;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;

public class Aims {
    
    private static Store storeItems = new Store(20);
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Some add
        storeItems.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        storeItems.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 120, 24.95f));
        storeItems.addMedia(new CompactDisc("CD Album", "Music", "Artist", 60, 15.99f, "Famous Artist"));
        
        showMenu(scanner); //Main menu
    }

    public static void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("AIMS: ");
            System.out.println("--------------------------------");
            System.out.println("1. View store");
            System.out.println("2. Update store");
            System.out.println("3. See current cart");
            System.out.println("0. Exit");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                	storeItems.print();
                    storeMenu(scanner);
                    break;
                case 2:
                	updateStore(scanner);
                    break;
                case 3:
                    cartMenu(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void storeMenu(Scanner scanner) {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewMediaDetails(scanner);
                    break;
                case 2:
                    addToCart(scanner);
                    break;
                case 3:
                    playMedia(scanner);
                    break;
                case 4:
                    seeCart();
                    cartMenu(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
 
    public static void viewMediaDetails(Scanner scanner) {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine();
        
        Media media = findMediaByTitle(title);
        if (media != null) {
            System.out.println("Media details: ");
            System.out.println(media);
            mediaDetailsMenu(scanner,media);      
        } else {
            System.out.println("Media with title '" + title + "' not found.");
        }
    }

    public static void mediaDetailsMenu(Scanner scanner,Media media) {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
            	addToCartInDetails(media);
                break;
            case 2:
            	playMediaInDetails(media);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void addToCart(Scanner scanner) {
        System.out.print("Enter the title of the media you want to add to the cart: ");
        String title = scanner.nextLine();
        Media media = findMediaByTitle(title);
        if (media != null) {
            cart.addMedia(media);
            System.out.println("Added to cart. Total items in cart: " + cart.getSize());
        } else {
            System.out.println("Media not found.");
        }
    }
    
    public static void addToCartInDetails(Media media) {
          	cart.addMedia(media);
            System.out.println("Added to cart. Total items in cart: " + cart.getSize());
    }

    public static void playMedia(Scanner scanner) {
        System.out.print("Enter the title of the media you want to play: ");
        String title = scanner.nextLine();
        Media media = findMediaByTitle(title);

        if (media != null && media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("This media cannot be played.");
        }
    }

    public static void playMediaInDetails(Media media) {
        if (media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("This media cannot be played.");
        }
    }

    public static void seeCart() {
        System.out.println("Current Cart: ");
        cart.print();
    }
    public static void updateStore(Scanner scanner) {
        System.out.println("Update Store:");
        System.out.println("1. Add a media to the store");
        System.out.println("2. Remove a media from the store");
        System.out.println("0. Back");
        System.out.println("Please choose a number: 0-1-2");

        int choice = scanner.nextInt();
        scanner.nextLine(); 
        switch (choice) {
            case 1:
                addMediaToStore(scanner);
                break;
            case 2:
                removeMediaFromStore(scanner);
                break;
            case 0:
                System.out.println("Back to main menu.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    
    private static void addMediaToStore(Scanner scanner) {
        System.out.println("Enter the type of media to add (1. DVD, 2. CD 3.Book): ");
        int mediaType = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter cost: ");
        float cost = scanner.nextFloat();
        scanner.nextLine(); 

        Media newMedia = null;
        switch (mediaType) {
            case 1: 
                System.out.print("Enter director: ");
                String directorDvd = scanner.nextLine();
                System.out.print("Enter length: ");
                int lengthDvd = scanner.nextInt();
                scanner.nextLine(); 
                newMedia = new DigitalVideoDisc(title, category, directorDvd, lengthDvd, cost);
                break;
            case 2: 
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();
                System.out.print("Enter director: ");
                String directorCd = scanner.nextLine();
                newMedia = new CompactDisc(title, category, directorCd, 0, cost, artist);
                break;
            case 3: 
                newMedia = new Book(title, category, cost);
                System.out.println("Would you like to add authors to this book? (yes/no): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("yes")) {
                    Book book = (Book) newMedia; 
                    while (true) {
                        System.out.print("Enter author's name (or type 'done' to finish): ");
                        String author = scanner.nextLine();
                        if (author.equalsIgnoreCase("done")) break;
                        book.addAuthor(author);
                    }
                }
                break;
            default:
                System.out.println("Invalid media type.");
                return;
        }

        if (newMedia != null) {
            storeItems.addMedia(newMedia);
            System.out.println("Media added to store successfully.");
        }
    }


    private static void removeMediaFromStore(Scanner scanner) {
        System.out.print("Enter the title of the media to remove: ");
        String title = scanner.nextLine();

        Media mediaToRemove = null;
        for (Media media : storeItems.getItemsInStore()) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                mediaToRemove = media;
                break;
            }
        }

        if (mediaToRemove != null) {
            storeItems.removeMedia(mediaToRemove);
            System.out.println("Media removed from store successfully.");
        } else {
            System.out.println("Media not found in the store.");
        }
    }

    public static void cartMenu(Scanner scanner) {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter medias in cart");
            System.out.println("2. Sort medias in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4-5: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    filterMediaInCart(scanner);
                    break;
                case 2:
                    sortMediaInCart(scanner);
                    break;
                case 3:
                    removeMediaFromCart(scanner);
                    break;
                case 4:
                    playMedia(scanner);
                    break;
                case 5:
                    placeOrder();
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static Media findMediaByTitle(String title) {
        for (Media media : storeItems.getItemsInStore()) {  
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;  
    }

    public static void placeOrder() {
        System.out.println("Order placed successfully.");
        cart.clear();
    }

    public static void filterMediaInCart(Scanner scanner) {
        System.out.println("Filter options: ");
        System.out.println("1. By ID");
        System.out.println("2. By Title");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                System.out.println("Enter the ID of the media: ");
                int id = scanner.nextInt();
                scanner.nextLine(); 
                cart.searchById(id); 
                break;
            case 2:
                System.out.println("Enter the title of the media: ");
                String title = scanner.nextLine();
                cart.searchByTitle(title); 
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void sortMediaInCart(Scanner scanner) {
        System.out.println("Sort options: ");
        System.out.println("1. By Title");
        System.out.println("2. By Cost");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        switch (choice) {
            case 1:
                cart.getItemsOrdered().sort(Media.COMPARE_BY_TITLE_COST);  
                System.out.println("Sorted by Title:");
                cart.print();  
                break;
            case 2:
                cart.getItemsOrdered().sort(Media.COMPARE_BY_COST_TITLE);  
                System.out.println("Sorted by Cost:");
                cart.print();  
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void removeMediaFromCart(Scanner scanner) {
        System.out.print("Enter the title of the media to remove: ");
        String title = scanner.nextLine();
        Media media = findMediaByTitle(title);
        if (media != null) {
            cart.removeMedia(media);
            System.out.println("Removed " + title + " from cart.");
        } else {
            System.out.println("Media not found in cart.");
        }
    }
}
