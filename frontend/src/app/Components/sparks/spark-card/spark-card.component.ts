import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Spark } from 'src/app/models/spark.model';
import { CookieService } from 'ngx-cookie-service';
import { SparkService } from 'src/app/services/spark.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-spark-card',
  templateUrl: './spark-card.component.html',
  styleUrls: ['./spark-card.component.css']
})
export class SparkCardComponent {

  @Input() spark: Spark = new Spark();
  @Output() editSpark: EventEmitter<Spark> = new EventEmitter<Spark>();

  constructor(
    private sparkService: SparkService,
    private cookie: CookieService,
    private router: Router
  ) { }

  isDeleted: boolean = false;

  isUserCreator(): boolean {
    return this.spark.creator.username === this.cookie.get('username');
  }

  isUserAdmin(): boolean {
    return this.cookie.get('role') === 'ADMIN';
  }

  isLoggedIn(): boolean {
    return this.cookie.get('accessToken') !== '';
  }

  weightSpark() {
    if (this.isLoggedIn() === false) return;
    this.sparkService.updateLightCount(this.spark).subscribe({
      error: (err: any) => console.log(err),
      next: () => this.spark.lightCount++
    });
  }

  emitEditSpark() {
    this.editSpark.emit(this.spark);
  }

  deleteSpark() {
    this.sparkService.deleteSpark(this.spark).subscribe({
      error: (err: any) => {
        console.log(err);
      },
      next: () => {
        this.isDeleted = true;
        this.router.navigate(['/view-sparks'])
      }
    })
  }
}
