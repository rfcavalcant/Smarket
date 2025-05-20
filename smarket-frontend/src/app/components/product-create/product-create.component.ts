import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product-create',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  productForm!: FormGroup;

  submitted = false;
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder, private productService: ProductService) {}

  ngOnInit(): void {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      price: [0, [Validators.required, Validators.min(0.01)]],
      quantity: [0, [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit() {
    this.submitted = true;
    this.successMessage = '';
    this.errorMessage = '';

    if (this.productForm.valid) {
      this.productService.createProduct(this.productForm.value).subscribe({
        next: (res) => {
          this.successMessage = 'Produto criado com sucesso!';
          this.productForm.reset();
          this.submitted = false;
        },
        error: (err) => {
          this.errorMessage = 'Erro ao criar produto. Tente novamente.';
          console.error(err);
        }
      });
    }
  }
}
