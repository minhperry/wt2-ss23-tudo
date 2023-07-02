import { Component, OnDestroy, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Spark } from 'src/app/models/spark.model';
import { SparkService } from 'src/app/services/spark.service';

@Component({
  selector: 'app-view-sparks',
  templateUrl: './view-sparks.component.html',
  styleUrls: ['./view-sparks.component.css']
})
export class ViewSparksComponent implements OnInit, OnDestroy {

  listOfSparks: Array<Spark> = [];
  selectedSortOption: string = "";
  selectedFilterOption: string = "";

  editSpark: Spark | null = null;
  errorMessage: string | null = null;

  constructor(private sparkService: SparkService, private cookie: CookieService) {
  }

  ngOnDestroy(): void {
  }

  // Test spark display
  ngOnInit(): void {
    this.loadSparks();
  }

  loadSparks(): void {
    this.sparkService.getListOfSparks().subscribe({
      next: (sparks: Array<Spark>) => this.listOfSparks = sparks,
      error: () => this.errorMessage = 'Could not load sparks'
    });
  }

  receiveEditSpark(spark: Spark | null) {
    this.editSpark = spark;
  }

  receiveCancelEditSpark(spark: Spark | null) {
    this.editSpark = null;
  }

  userIsLoggedIn(): boolean {
    return this.cookie.get('username') !== '';
  }

  onSortOptionChange(): void {
    switch (this.selectedSortOption) {
      case "most-supports": {
        this.listOfSparks.sort((a, b) => b.lightCount - a.lightCount);
        break;
      }
      case "latest": {
        this.listOfSparks.sort((a, b) => {
          return b.createdAt.getTime() - a.createdAt.getTime();
        });
        break;
      }
      default: {
        break;
      }
    }
  }

  onFilterOptionChange(): void {
    switch (this.selectedFilterOption) {
      case "my-sparks": {
        const currentUser = this.cookie.get('username');
        this.listOfSparks = this.listOfSparks.filter(spark => spark.creator.username === currentUser);
        this.onSortOptionChange();
        break;
      }
      case "all-sparks": {
        this.loadSparks();
        this.onSortOptionChange();
        break;
      }
      default: {
        break;
      }
    }
  }
}
