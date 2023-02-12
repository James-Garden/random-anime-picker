<#macro htmlForm
  name="form"
  method="post"
  actionUrl=""
>
  <form name="${name}" method="${method}" action="${actionUrl}" style="width: 100%">
    <#nested>
  </form>
</#macro>
