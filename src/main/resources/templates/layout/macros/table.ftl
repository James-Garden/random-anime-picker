<#macro table class="">
  <table class="${class}">
    <#nested>
  </table>
</#macro>


<#macro simpleRow keyText valueText>
  <tr>
    <td class="table-row-key">${keyText}</td>
    <td class="table-row-value">${valueText}</td>
  </tr>
</#macro>
