<#macro htmlForm
  name="form"
  method="post"
  actionUrl=""
>
  <form name="${name}" method="${method}" action="${actionUrl}">
    <#nested>
  </form>
</#macro>
