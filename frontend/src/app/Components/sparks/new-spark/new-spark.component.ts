import { Component, EventEmitter, OnDestroy, OnInit } from '@angular/core';
import { Spark } from 'src/app/models/spark.model';
import { SparkService } from 'src/app/services/spark.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-spark',
  templateUrl: './new-spark.component.html',
  styleUrls: ['./new-spark.component.css']
})
export class NewSparkComponent implements OnInit, OnDestroy {

  spark: Spark = new Spark();
  errorMessage: string | null = null;

  constructor(private sparkService: SparkService, private router: Router) {
  }

  ngOnDestroy(): void {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.spark.content.length > 500) {
      this.errorMessage = 'Spark content is too long';
      return;
    } else if (this.spark.content.length === 0) {
      this.errorMessage = 'Spark content is empty';
      return;
    }

    this.sparkService.createNewSpark(this.spark).subscribe({
      error: (err: any) => {
        this.errorMessage = 'Could not create spark'
        console.log(err);
      },
      next: () => {
        // I fucking give up on flawless reload, this is the nearest I could get
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/view-sparks']);
        });
      }
    });
  }
}