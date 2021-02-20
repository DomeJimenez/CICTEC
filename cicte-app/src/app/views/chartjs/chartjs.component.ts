import { Component } from '@angular/core';

@Component({
  templateUrl: 'chartjs.component.html'
})
export class ChartJSComponent {

  // lineChart
  public lineChartData: Array<any> = [
    {data: [0.006225,
            0.013671,
            0.023071,
            0.029296,
            0.037719,
            0.281009,
            1.980835,
            6.089233,
            14.11292,
            27.42249,
            48.38037,
            77.63757,
            114.0083,
            158.6157,
            209.3317,
            267.5833,
            336.5807,
            416.6434],
        label: 'Altura en nudos'},
  ];

  public lineChartRuta: Array<any> = [
    {data: [-0.002168,
            -0.000900,
            -0.001861,
            -0.003763,
            -0.001860,
            -0.007928,
            -0.042659,
            0.128934,
            0.295435,
            0.553492,
            0.934621,
            -1.348539,
            -1.634763,
            -1.322222,
            0.0635395,
            1.8389320,
            2.8901940,
            3.5940210
      ], label: 'Plan Ruta'}
  ];
  public lineChartLabels: Array<any> = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18];

  public lineChartLabelsRuta: Array<any> = [0.0131835,
    0.0069580,
    -0.014831,
    -0.021972,
    -0.181701,
    -1.295288,
    -3.937622,
    -9.278992,
    -18.26025,
    -32.71252,
    -53.08014,
    -78.63904,
    -110.2255,
    -146.1395,
    -187.2039,
    -235.4598,
    -290.8726,
    -0.023742];
  public lineChartOptions: any = {
    animation: false,
    responsive: true
  };
  public lineChartColours: Array<any> = [
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // dark grey
      backgroundColor: 'rgba(77,83,96,0.2)',
      borderColor: 'rgba(77,83,96,1)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    },
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
  public lineChartLegend = true;
  public lineChartType = 'line';

  // barChart
  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabels: string[] = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
  public barChartType = 'bar';
  public barChartLegend = true;

  public barChartData: any[] = [
    {data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A'},
    {data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B'}
  ];

  // Doughnut
  public doughnutChartLabels: string[] = ['Download Sales', 'In-Store Sales', 'Mail-Order Sales'];
  public doughnutChartData: number[] = [350, 450, 100];
  public doughnutChartType = 'doughnut';

  // Radar
  public radarChartLabels: string[] = ['Confianza', 'Estabilidad', 'Calma', 'Reacción', 'Control', 'Velocidad', 'Tiempo'];

  public radarChartData: any = [
    {data: [65, 59, 90, 81, 56, 55, 40], label: 'Plan'},
    {data: [28, 48, 40, 19, 96, 27, 100], label: 'Evaluación'}
  ];
  public radarChartType = 'radar';

  // Pie
  public pieChartLabels: string[] = ['Download Sales', 'In-Store Sales', 'Mail Sales'];
  public pieChartData: number[] = [300, 500, 100];
  public pieChartType = 'pie';

  // PolarArea
  public polarAreaChartLabels: string[] = ['Download Sales', 'In-Store Sales', 'Mail Sales', 'Telesales', 'Corporate Sales'];
  public polarAreaChartData: number[] = [300, 500, 100, 40, 120];
  public polarAreaLegend = true;

  public polarAreaChartType = 'polarArea';

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }

}
