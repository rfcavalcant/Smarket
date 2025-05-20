import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Product {
  id?: number;
  name: string;
  description: string;
  price: number;
  quantity: number;
}

export interface StockStatus {
  id: number;
  name: string;
  quantity: number;
  lowStock: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productUrl = 'http://localhost:8080/products'; // Porta do seu serviço de produtos
  private stockUrl = 'http://localhost:8081/stock';      // Porta do seu serviço de estoque

  constructor(private http: HttpClient) { }

  listProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productUrl);
  }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.productUrl}/${id}`);
  }

  createProduct(product: Product): Observable<Product> {
  if (product.id) {
    // PUT para atualizar
    return this.http.put<Product>(`${this.productUrl}/${product.id}`, product);
  } else {
    // POST para criar
    return this.http.post<Product>(this.productUrl, product);
  }
}

  getStockStatus(id: number): Observable<StockStatus> {
    return this.http.get<StockStatus>(`${this.stockUrl}/${id}`);
    
  }

  deleteProduct(id: number): Observable<void> {
  return this.http.delete<void>(`${this.productUrl}/${id}`);
}

}
