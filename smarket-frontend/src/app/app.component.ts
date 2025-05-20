import { Component, OnInit } from '@angular/core';
import { ProductService, Product, StockStatus } from './services/product.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [FormsModule, CommonModule, HttpClientModule],
})
export class AppComponent implements OnInit {
  title = 'smarket-frontend';
  products: Product[] = [];
  stockMap: { [productId: number]: StockStatus } = {};

  showForm = false;
  editingProduct: Product | null = null;
  formProduct: Product = this.createEmptyProduct();

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.listProducts().subscribe((data) => {
      this.products = data;
      data.forEach(product => {
        if (product.id !== undefined) {
          this.productService.getStockStatus(product.id).subscribe(stock => {
            this.stockMap[product.id!] = stock;
          });
        }
      });
    });
  }

  createEmptyProduct(): Product {
    return { name: '', description: '', price: 0, quantity: 0 };
  }

  showCreateForm() {
    this.formProduct = this.createEmptyProduct();
    this.editingProduct = null;
    this.showForm = true;
  }

  showEditForm(product: Product) {
    this.formProduct = { ...product };
    this.editingProduct = product;
    this.showForm = true;
  }

  cancelForm() {
    this.showForm = false;
    this.editingProduct = null;
  }

  saveProduct() {
  console.log('Salvar produto:', this.formProduct);
  if (this.editingProduct && this.editingProduct.id) {
    this.productService.createProduct({ ...this.formProduct, id: this.editingProduct.id }).subscribe(() => {
      console.log('Produto atualizado');
      this.loadProducts();
      this.showForm = false;
    }, error => {
      console.error('Erro ao atualizar produto:', error);
    });
  } else {
    this.productService.createProduct(this.formProduct).subscribe(() => {
      console.log('Produto criado');
      this.loadProducts();
      this.showForm = false;
    }, error => {
      console.error('Erro ao criar produto:', error);
    });
  }
}

deleteProduct(product: Product) {
  console.log('Deletar produto:', product);
  if (product.id !== undefined) {
    this.productService.deleteProduct(product.id).subscribe(() => {
      console.log('Produto deletado');
      this.loadProducts();
    }, error => {
      console.error('Erro ao deletar produto:', error);
    });
  }
}
}
