/* (c) Tyler Holley January 22, 2018
 * CitationGenerator Version 1.0
 * 
 * User inputs academic source information and gets a proper citation for either copying to the clipboard completely formatted or output to the terminal for manual formatting.
 */

import java.util.Scanner;
import java.awt.datatransfer.*; // First import for clipboard copying
import java.awt.Toolkit; // Second import for clipboard copying


class CitationGenerator {
	public static String bookFormat(String author, String title, String publisher, int pubDate) {
		String bFormat = author + ". " + title + ", " + publisher + ", " + pubDate + ".";
		
		return bFormat;
	}
	
	public static String siteFormat(String author, String siteName, String publisher, String pubFull, String url, String accessDate) {//Need to add date after consulting Mr. Blake.
		String sFormat = author + ". " + siteName + ". " + publisher + ", " + pubFull + ", " + url + ". Accessed " + accessDate + ".";

		return sFormat;
	} 
	
	public static String pageFormat(String author, String pageName, String siteName, String publisher, String pubFull, String url, String accessDate) {
		String pFormat = author + ". \"" + pageName + ".\" "+ siteName + ". " + publisher + ", " + pubFull + ", " + url + ". Accessed " + accessDate + ".";
		
		return pFormat;
	}
	
	public static String dateMerge(String pubY, String pubMB4, String pubD) {
		String pubM = pubMB4.substring(0,3) + "."; 
		String pubFull = pubD + " " + pubM + " "+ pubY;
		
		return pubFull;
	}
		
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String error1 = "\nERROR 001: Invalid input. Please rerun the program."; // Catchall error
		String errorN = "\nERROR 002: That other format is currently not supported in this version. Please rerun the program."; // Filler error while WIP
		
		System.out.println("Welcome to Tyler Holley's CitationGenerator v1!");
		System.out.print("What format is your citation in? MLA/APA: "); // Add APA/Chicago support
		String format = in.next();
			
		if (format.equalsIgnoreCase("MLA")) { // equalsIgnoreCase ignores case inputted so MLA, mLa, mla, etc. are all valid
			System.out.print("\nIs your source a book, website, or other? ");
			String sourceType = in.next();
			// Try and figure out how to clear the console after the user inputs sourceType 
			
			if (sourceType.equalsIgnoreCase("book")) {
				System.out.print("\nAuthor's First Name: ");
				String fName = in.next();
				
				System.out.print("Author's Last Name: ");
				String lName = in.next();
				in.nextLine();
				
				System.out.print("\nBook Title: ");
				String title = in.nextLine();
				
				System.out.print("\nPublisher Name: ");
				String publisher = in.nextLine();
				
				System.out.print("\nPublication Year: ");
				int pubDate = in.nextInt();
				
				String author = lName + ", " + fName; // Converts fName and lName to one concatonated String
				
				System.out.print("\n\nYour citation is ready, would you like to save it to the clipboard? y/n ");
				String option = in.next();

				if (option.equalsIgnoreCase("y") || option.equalsIgnoreCase("yes")) {
					String doneFormatting = bookFormat(author, title, publisher, pubDate);
					StringSelection stringSelection = new StringSelection(doneFormatting);
					Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
					clpbrd.setContents(stringSelection, null); 
					
					System.out.println("\n\nYour citation is copied to your clipboard! Don't forget to italicize the site name!\n");
				}
				
				else {
					System.out.println("\n\nHere is your citation! Don't forget to italicize the title!\n");
					System.out.println(bookFormat(author, title, publisher, pubDate));
				}
			}
			
			else if (sourceType.equalsIgnoreCase("website")) {
				System.out.print("\nIs it an entire website (Example: wikihow.com) or a specific page (Example: \"How To Make Pasta\")? Site/Page: ");
				String webType = in.next();
				
				if (webType.equalsIgnoreCase("site")) {
					System.out.print("\n\nAuthor First Name: ");
					String fName = in.next();
					System.out.print("Author Last Name: ");
					String lName = in.next();
					in.nextLine();
					String author = lName + ", " + fName;
					
					System.out.print("\nName of Site (Example: wikiHow): ");
					String siteName = in.nextLine();
					
					System.out.print("\nSite Publsiher: ");
					String publisher = in.nextLine();
					
					System.out.print("\nPublication Day: ");
					int pubDay = in.nextInt();
					String pubD = Integer.toString(pubDay);
					
					System.out.print("Publication Month (Name of Month): ");
					String pubMB4 = in.next();
					
					System.out.print("Publication Year: ");
					int pubYear = in.nextInt();
					String pubY = Integer.toString(pubYear);
					
					String pubFull = dateMerge(pubY, pubMB4, pubD);					
					
					System.out.print("\nWebsite URL (Example: www.wikihow.com/): ");
					String url = in.next();
					
					System.out.println("\nDate Accessed");
					
					System.out.print("Day: ");
					int accessDay = in.nextInt();
					String accessD = Integer.toString(accessDay);
					
					System.out.print("Month (Name of month): ");
					String accessMB4 = in.next();
					
					System.out.print("Year: ");
					int accessYear = in.nextInt();
					String accessY = Integer.toString(accessYear);
					
					String accessDate = dateMerge(accessD, accessMB4, accessY);
					
						
					System.out.print("\n\nYour citation is ready, would you like to save it to the clipboard? y/n ");
					String option = in.next();

					if (option.equalsIgnoreCase("y") || option.equalsIgnoreCase("yes")) {
						String doneFormatting = siteFormat(author, siteName, publisher, pubFull, url, accessDate);
						StringSelection stringSelection = new StringSelection(doneFormatting);
						Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
						clpbrd.setContents(stringSelection, null); 
						
						System.out.println("\n\nYour citation is copied to your clipboard! Don't forget to italicize the book title!\n");
					}
					
					else {
						System.out.println("\n\nHere is your citation! Don't forget to italicize the site name!\n");
						System.out.println(siteFormat(author, siteName, publisher, pubFull, url, accessDate));
					}
				}
				
				else if (webType.equalsIgnoreCase("page")) {
					System.out.print("\033[H\033[2J");			
							
					System.out.print("Author First Name: ");
					String fName = in.next();
					System.out.print("Author Last Name: ");
					String lName = in.next();
					in.nextLine();
					String author = lName + ", " + fName;
					
					System.out.print("\n Page or article title: ");
					String pageTitle = in.nextLine();
					
					System.out.print("\nName of Site (Example: wikiHow): ");
					String siteName = in.nextLine();
					
					System.out.print("\n\nSite Publsiher: ");
					String publisher = in.nextLine();
					
					System.out.print("\nPublication Day: ");
					int pubDay = in.nextInt();
					String pubD = Integer.toString(pubDay);
					
					System.out.print("Publication Month (Name of Month): ");
					String pubMB4 = in.next();
					
					System.out.print("Publication Year: ");
					int pubYear = in.nextInt();
					String pubY = Integer.toString(pubYear);
					
					String pubFull = dateMerge(pubY, pubMB4, pubD);					
					
					System.out.print("\nWebsite URL (Example: www.wikihow.com/): ");
					String url = in.next();
					
					System.out.println("\nDate Accessed");
					
					System.out.print("Day: ");
					int accessDay = in.nextInt();
					String accessD = Integer.toString(accessDay);
					
					System.out.print("Month (Name of month): ");
					String accessMB4 = in.next();
					
					System.out.print("Year: ");
					int accessYear = in.nextInt();
					String accessY = Integer.toString(accessYear);
					
					String accessDate = dateMerge(accessD, accessMB4, accessY);
					
					
									
				}
				
				else {
					System.out.println(error1);
				}
			}
			
			else if (sourceType.equalsIgnoreCase("other")) {
				System.out.println(errorN);
			}
			
			else {
				System.out.println(error1);
			}
		}
		
		else {
			System.out.println(error1);	
			// Version 2 will contain APA and Chicago formats.
		}
	}
}