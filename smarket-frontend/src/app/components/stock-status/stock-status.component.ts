import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService, StockStatus } from '../../services/product.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-stock-status',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './stock-status.component.html',
  styleUrls: ['./stock-status.component.css']
})
export class StockStatusComponent {
  productId: number | null = null;
  stockStatus: StockStatus | null = null;
  errorMessage = '';

  constructor(private productService: ProductService) {}

  checkStock() {
    if (this.productId != null) {
      this.productService.getStockStatus(this.productId).subscribe({
        next: (status) => {
          this.stockStatus = status;
          this.errorMessage = '';
        },
        error: () => {
          this.errorMessage = 'Produto não encontrado ou erro na consulta.';
          this.stockStatus = null;
        }
      });
    }
  }
}
