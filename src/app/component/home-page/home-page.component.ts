import { Component } from '@angular/core';
import { DataService } from 'src/app/service/data.service';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ExchangeOutput } from 'src/app/model/ExchangeOutput';


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {
  totalValue: ExchangeOutput;
  currencyCode: String = "";
  availableCurrencies: String[] = ["USD", "EUR", "GBP"]
  currency: string = "USD";

  requestForm: FormGroup = new FormGroup({
    requestValue: new FormControl('', [Validators.required, Validators.pattern("^[0-9.]*$")]),
    currency: new FormControl('')
  });

  constructor(private dataService: DataService) { 
    this.totalValue = {value: 0, exchangeRate: 0};
  }

  reset(){
    this.totalValue.exchangeRate = 0;
    this.totalValue.value = 0;
  }

  getFormControls(){
    return this.requestForm.controls;
  }

  onSubmit(){
    this.dataService.getTotal(this.requestForm.controls.requestValue.value, this.currency).subscribe(resp => {
      console.log("resp");
      console.log(resp);
      if(resp.body != null){
        this.totalValue = resp.body;
      }
    });
  }

}
