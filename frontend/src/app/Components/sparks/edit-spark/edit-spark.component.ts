import { Component, OnDestroy, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Spark } from 'src/app/models/spark.model';
import { SparkService } from 'src/app/services/spark.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-spark',
  templateUrl: './edit-spark.component.html',
  styleUrls: ['./edit-spark.component.css']
})
export class EditSparkComponent implements OnInit, OnDestroy {

  @Input() spark: Spark = new Spark();
  @Output() editSpark: EventEmitter<Spark> = new EventEmitter<Spark>();

  errorMessage: string | null = null;

  constructor(
    private sparkService: SparkService,
    private router: Router
  ) { }

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

    this.sparkService.updateSpark(this.spark).subscribe({
      error: (error: any) => {
        this.errorMessage = 'Could not update spark'
        console.log(error);
      },
      next: () => {
        this.editSpark.emit(undefined);
        this.router.navigate(['/view-sparks'])
      }
    });
  }

  emitCancelEditSpark() {
    this.editSpark.emit(undefined);
  }
}