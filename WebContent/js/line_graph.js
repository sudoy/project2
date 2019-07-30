$(function ()
{
    const config = {
        type: 'line',
        data: barChartData
        }

    const context = jQuery("#chart")
    const chart = new Chart(context,config)
});

// ----------------------------------------------------------------------
const barChartData = {
    labels : ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
    datasets : [
        {
        label: "2019年",
        lineTension: 0,
        data : [1417,1303,1395,1476,1439,1462],
        backgroundColor: 'rgba(255, 166, 163, 0.3)',
        borderColor: 'rgba(255, 166, 163, 0.8)'
        },
        {
        label: "2018年",
        lineTension: 0,
        data : [935,910,1006,1048,1089,1082,1218,1257,1215,1294,1336,1476],
        backgroundColor: 'rgba(105, 146, 179, 0.3)',
        borderColor: 'rgba(105, 146, 179, 0.8)'
        }
    ]
}