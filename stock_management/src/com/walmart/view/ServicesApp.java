package com.walmart.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Employee;
import com.walmart.pojo.Product;
import com.walmart.pojo.Purchase;
import com.walmart.pojo.Sale;
import com.walmart.pojo.Supplier;
import com.walmart.services.EmployeeService;
import com.walmart.services.ProductService;
import com.walmart.services.PurchaseService;
import com.walmart.services.SaleService;
import com.walmart.services.SupplierService;

public class ServicesApp {
	
	private static void productServices(Scanner sc) {
		
		ProductService pService;
		try {
			pService = new ProductService();
		} catch (SQLException e1) {
			System.out.println("Unable to connect to database/table try again");
			e1.printStackTrace();
			return;
		}
		do {
			System.out.println(
					"1.Add Product(Id, Name, Cost, Quantity)\n2.Update Product\n3.Delete Product\n4.Get Product By Id \n5.Get All Products\n6.Go back to previous menu");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("Enter the product details");
				Product p = new Product(sc.nextInt(), sc.next(), sc.nextDouble(), sc.nextInt());
				int i;
				try {
					i = pService.addProduct(p);
					if (i > 0) {
						System.out.println(i + " product added");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				System.out.println("Enter Id of product to update");
				try {
					Product pro = pService.getProdcutById(sc.nextInt());
					System.out.println("Enter the qantity");
					int qty = sc.nextInt();
					System.out.println("Enter the cost");
					double cost = sc.nextDouble();
					int j = pService.editProdcut(qty, cost, pro.getPid());
					if (j > 0) {
						System.out.println(pro.getPid() + " updated");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException e) {
					
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter Id of product to delete");
				try {
					int k = pService.removeProduct(sc.nextInt());
					if (k > 0) {
						System.out.println("Deleted");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter Id of product to retrieve");
				try {
					Product product = pService.getProdcutById(sc.nextInt());
					System.out.println(product);
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("List of Products");
				try {
					List<Product> prodcuts = pService.getAll();
					prodcuts.forEach(p1 -> System.out.println(p1));
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid");
			}

		} while (true);
	}
	
	
	
	private static void supplierServices(Scanner sc) {
		
		SupplierService sService;
		try {
			sService = new SupplierService();
		} catch (SQLException e1) {
			System.out.println("Unable to connect to database/table try again");
			e1.printStackTrace();
			return;
		}
		do {
			System.out.println(
					"1.Add Supplier(Id, Name, Rating)\n2.Update Supplier\n3.Delete Supplier\n4.Get Supplier By Id \n5.Get All Suppliers\n6.Go back to previous menu");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("Enter the supplier details");
				Supplier s = new Supplier(sc.nextInt(), sc.next(), sc.nextDouble());
				int i;
				try {
					i = sService.addSupplier(s);
					if (i > 0) {
						System.out.println(i + " supplier added");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				System.out.println("Enter Id of supplier to update");
				try {
					Supplier sup = sService.getSupplierById(sc.nextInt());
					System.out.println("Enter new rating");
					double rating = sc.nextDouble();
					int j = sService.editSupplier(rating, sup.getSid());
					if (j > 0) {
						System.out.println(sup.getSid() + " updated");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException e) {
					
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter Id of supplier to delete");
				try {
					int k = sService.removeSupplier(sc.nextInt());
					if (k > 0) {
						System.out.println("Deleted");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter Id of Supplier to retrieve");
				try {
					Supplier supplier = sService.getSupplierById(sc.nextInt());
					System.out.println(supplier);
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("List of Suppliers");
				try {
					List<Supplier> suppliers = sService.getAll();
					suppliers.forEach(s1 -> System.out.println(s1));
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid");
			}

		} while (true);
	}

	private static void employeeServices(Scanner sc) {
		
		EmployeeService eService;
		try {
			eService = new EmployeeService();
		} catch (SQLException e1) {
			System.out.println("Unable to connect to database/table try again");
			e1.printStackTrace();
			return;
		}
		do {
			System.out.println(
					"1.Add Employees(Id, Name, Salary, Active/Resigned)\n2.Update Employee\n3.Delete Employee\n4.Get Employee By Id \n5.Get All Employees\n6.Go back to previous menu");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("Enter the employee details");
				Employee e = new Employee(sc.nextInt(), sc.next(), sc.nextDouble(), sc.next());
				int i;
				try {
					i = eService.addEmployee(e);
					if (i > 0) {
						System.out.println(i + " employee added");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException err) {
					System.out.println(err.getMessage());
				}

				break;
			case 2:
				System.out.println("Enter Id of employee to update");
				try {
					Employee emp = eService.getEmployeeById(sc.nextInt());
					System.out.println("Enter the salary");
					double salary = sc.nextDouble();
					System.out.println("Enter Active/Resigned");
					String active = sc.next();
					int j = eService.editEmployee(salary, active, emp.getEid());
					if (j > 0) {
						System.out.println("Employee " + emp.getEid() + " updated");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException err) {
					
					System.out.println(err.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter Id of employee to delete");
				try {
					int k = eService.removeEmployee(sc.nextInt());
					if (k > 0) {
						System.out.println("Deleted");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException err) {
					System.out.println(err.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter Id of employee to retrieve");
				try {
					Employee employee = eService.getEmployeeById(sc.nextInt());
					System.out.println(employee);
				} catch (StockException err) {
					System.out.println(err.getMessage());
				}
				break;
			case 5:
				System.out.println("List of Employee");
				try {
					List<Employee> employees = eService.getAll();
					employees.forEach(p1 -> System.out.println(p1));
				} catch (StockException err) {
					System.out.println(err.getMessage());
				}
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid");
			}

		} while (true);
	}
	
	private static void saleServices(Scanner sc) {
		
		SaleService sService;
		ProductService pService;
		try {
			sService = new SaleService();
			pService = new ProductService();
		} catch (SQLException e1) {
			System.out.println("Unable to connect to database/table try again");
			e1.printStackTrace();
			return;
		}
		do {
			System.out.println(
					"1.Add Sale(Sale Id, Product Id, Quantity)\n2.Return Sale\n3.Get Sale By Id \n4.Get All Sales\n5.Go back to previous menu");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("Enter the sale details");
				try {
					List<Product> products= pService.getAll();
					products.forEach(p1 -> System.out.println(p1));
				} catch (StockException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int saleId = sc.nextInt();
				int pid = sc.nextInt();
				int qty = sc.nextInt();
				try {
					Product p = pService.getProdcutById(pid);
					Product pSell = new Product(pid, p.getPname(), p.getCost(), qty);
					Sale s = new Sale(saleId, pSell);
					int i = sService.addSale(s);
					if (i > 0) {
						System.out.println(i + " sale added");
						System.out.println("Total price = "+p.getCost()*qty);
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				System.out.println("Enter Id of sale to return");
				try {
					int k = sService.removeSale(sc.nextInt());
					if (k > 0) {
						System.out.println("Deleted");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter Id of sale to retrieve");
				try {
					Sale sale = sService.getSaleById(sc.nextInt());
					System.out.println(sale);
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("List of Sales");
				try {
					List<Sale> sales = sService.getAll();
					sales.forEach(s1 -> System.out.println(s1));
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				return;
			default:
				System.out.println("Invalid");
			}

		} while (true);
	}
	
	private static void purchaseServices(Scanner sc) {
		
		PurchaseService purchaseService;
		ProductService productService;
		SupplierService supplierService;
		try {
			purchaseService = new PurchaseService();
			productService = new ProductService();
			supplierService = new SupplierService();
		} catch (SQLException e1) {
			System.out.println("Unable to connect to database/table try again");
			e1.printStackTrace();
			return;
		}
		do {
			System.out.println(
					"1.Add Purchase(Purchase Id, Supplier Id, Product Id, Quantity, Status(Ordered/Received/Cancelled))\n2.Update Purchase status\n3.Get Purchase By Id \n4.Get All Purchases\n5.Go back to previous menu");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("Enter the purchase details");
				try {
					List<Product> products= productService.getAll();
					products.forEach(p1 -> System.out.println(p1));
					List<Supplier> suppliers= supplierService.getAll();
					suppliers.forEach(s1 -> System.out.println(s1));
					
					int i = purchaseService.addPurchase(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.next());
					if (i > 0) {
						System.out.println(i + " purchase added");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				System.out.println("Enter updated status(Ordered/Received/Cancelled) and purchase Id");
				try {
					int k = purchaseService.updatePurchase(sc.next(), sc.nextInt());
					if (k > 0) {
						System.out.println("Updated purchase");
					}
					else
						System.out.println("Operation failed! Try again");
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter Id of purchase to retrieve");
				try {
					Purchase pur = purchaseService.getPurchaseById(sc.nextInt());
					System.out.println(pur);
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("List of Sales");
				try {
					List<Purchase> purchases = purchaseService.getAll();
					purchases.forEach(p1 -> System.out.println(p1));
				} catch (StockException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				return;
			default:
				System.out.println("Invalid");
			}

		} while (true);
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("1. Product Services");
			System.out.println("2. Supplier Services");
			System.out.println("3. Employee Services");
			System.out.println("4. Sales Services");
			System.out.println("5. Purchase Services");
			System.out.println("6. Exit");
			
			switch(sc.nextInt()) {
			case 1:
				productServices(sc);
				break;
			case 2:
				supplierServices(sc);
				break;
			case 3:
				employeeServices(sc);
				break;
			case 4:
				saleServices(sc);
				break;
			case 5:
				purchaseServices(sc);
				break;
			case 6:
				System.out.println("Thank You");
				sc.close();
				System.exit(0);
			}
		}while(true);
	}
}
