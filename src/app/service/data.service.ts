import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ExchangeOutput } from '../model/ExchangeOutput';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  public getTotal(inputValue: number, currency: string): Observable<HttpResponse<(ExchangeOutput)>> {
    let payload = {
      "value": inputValue,
      "currency": currency
    }

    return this.http.post<ExchangeOutput>(environment.apiURL + "/api/v1/exchange", payload, { observe: 'response'});
  }
}
